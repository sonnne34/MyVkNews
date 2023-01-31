package com.sonne.myvknews.presentation.news

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.sonne.myvknews.R
import com.sonne.myvknews.domain.FeedPost
import com.sonne.myvknews.domain.StatisticItem
import com.sonne.myvknews.domain.StatisticType
import com.sonne.myvknews.ui.theme.Red

@Composable
fun CardPost(
    modifier: Modifier = Modifier,
    feedPost: FeedPost,
    onLikeClickListener: (StatisticItem) -> Unit,
    onCommentClickListener: (StatisticItem) -> Unit,
) {
    Card(
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            HeaderPost(feedPost)
            Spacer(modifier = Modifier.height(8.dp))
            ContentPost(feedPost)
            Spacer(modifier = Modifier.height(8.dp))
            StatisticsPost(
                statistics = feedPost.statistics,
                onLikeClickListener = onLikeClickListener,
                onCommentClickListener = onCommentClickListener,
                isFavourite = feedPost.isLiked
            )
        }
    }
}

@Composable
private fun HeaderPost(feedPost: FeedPost) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = feedPost.communityImageUrl,
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape),
            contentDescription = stringResource(R.string.description_avatar)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = feedPost.communityName)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = feedPost.publicationDate,
                color = MaterialTheme.colors.onSecondary
            )
        }
        Icon(
            imageVector = Icons.Rounded.MoreVert,
            tint = MaterialTheme.colors.onSecondary,
            contentDescription = stringResource(R.string.description_more)
        )
    }
}

@Composable
private fun ContentPost(feedPost: FeedPost) {
    Text(text = feedPost.contentText)
    Spacer(modifier = Modifier.height(8.dp))
    AsyncImage(
        model = feedPost.contentImageUrl,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        contentScale = ContentScale.FillWidth,
        contentDescription = stringResource(R.string.description_content)
    )
}

@Composable
private fun StatisticsPost(
    statistics: List<StatisticItem>,
    onLikeClickListener: (StatisticItem) -> Unit,
    onCommentClickListener: (StatisticItem) -> Unit,
    isFavourite: Boolean,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.weight(1f)
        ) {
            val viewItem = statistics.getItemByType(StatisticType.VIEWS)
            IconText(
                iconResource = R.drawable.ic_views_count,
                descriptionImage = R.string.description_views,
                countText = formatStatisticCount(viewItem.count),
            )
        }
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            val viewShares = statistics.getItemByType(StatisticType.REPOSTS)
            IconText(
                iconResource = R.drawable.ic_share,
                descriptionImage = R.string.description_share,
                countText = formatStatisticCount(viewShares.count),
            )
            val viewComments = statistics.getItemByType(StatisticType.COMMENTS)
            IconText(
                iconResource = R.drawable.ic_comment,
                descriptionImage = R.string.description_comment,
                countText = formatStatisticCount(viewComments.count),
                onItemClickListener = {
                    onCommentClickListener(viewComments)
                }
            )
            val viewLikes = statistics.getItemByType(StatisticType.LIKES)
            IconText(
                iconResource = if (isFavourite) {
                    R.drawable.ic_like_set
                } else {
                    R.drawable.ic_like
                },
                descriptionImage = R.string.description_like,
                countText = formatStatisticCount(viewLikes.count),
                onItemClickListener = {
                    onLikeClickListener(viewLikes)
                },
                tint = if (isFavourite) {
                    Red
                } else {
                    MaterialTheme.colors.onSecondary
                }
            )
        }
    }
}

private fun formatStatisticCount(count: Int): String {
    return if (count > 100_000) {
        String.format("%sK", (count / 1000))
    } else if (count > 1000) {
        String.format("%.1fK", (count / 1000f))
    } else {
        count.toString()
    }
}

private fun List<StatisticItem>.getItemByType(type: StatisticType): StatisticItem {
    return this.find { it.type == type } ?: throw IllegalStateException()
}

@Composable
private fun IconText(
    iconResource: Int,
    descriptionImage: Int,
    countText: String,
    onItemClickListener: (() -> Unit)? = null,
    tint: Color = MaterialTheme.colors.onSecondary,
) {
    val modifier = if (onItemClickListener == null) {
        Modifier
    } else {
        Modifier.clickable {
            onItemClickListener()
        }
    }
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(20.dp),
            painter = painterResource(id = iconResource),
            contentDescription = stringResource(descriptionImage),
            tint = tint
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = countText,
            color = MaterialTheme.colors.onSecondary
        )
    }
}
