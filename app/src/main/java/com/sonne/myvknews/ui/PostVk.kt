package com.sonne.myvknews.ui

import androidx.compose.foundation.Image
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sonne.myvknews.R
import com.sonne.myvknews.domain.FeedPost
import com.sonne.myvknews.domain.StatisticItem
import com.sonne.myvknews.domain.StatisticType

@Composable
fun CardPost(
    modifier: Modifier = Modifier,
    feedPost: FeedPost,
    onLikeClickListener: (StatisticItem) -> Unit,
    onViewsClickListener: (StatisticItem) -> Unit,
    onShareClickListener: (StatisticItem) -> Unit,
    onCommentClickListener: (StatisticItem) -> Unit
) {
    Card(
        modifier = modifier,
    ) {
        Column {
            HeaderPost(feedPost)
            Spacer(modifier = Modifier.height(8.dp))
            ContentPost(feedPost)
            Spacer(modifier = Modifier.height(8.dp))
            StatisticsPost(
                statistics = feedPost.statistics,
                onLikeClickListener = onLikeClickListener,
                onShareClickListener = onShareClickListener,
                onCommentClickListener = onCommentClickListener,
                onViewsClickListener = onViewsClickListener,
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
        Image(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape),
            painter = painterResource(id = feedPost.avatarResId),
            contentDescription = stringResource(R.string.description_avatar)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = feedPost.communityName)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = feedPost.dataPost,
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
    Image(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp),
        painter = painterResource(id = feedPost.contentImageResId),
        contentScale = ContentScale.FillWidth,
        contentDescription = stringResource(R.string.description_content)
    )
}

@Composable
private fun StatisticsPost(
    statistics: List<StatisticItem>,
    onLikeClickListener: (StatisticItem) -> Unit,
    onViewsClickListener: (StatisticItem) -> Unit,
    onShareClickListener: (StatisticItem) -> Unit,
    onCommentClickListener: (StatisticItem) -> Unit
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
                countText = viewItem.count.toString(),
                onItemClickListener = {
                    onViewsClickListener(viewItem)
                }
            )
        }
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            val viewShares = statistics.getItemByType(StatisticType.SHARES)
            IconText(
                iconResource = R.drawable.ic_share,
                descriptionImage = R.string.description_share,
                countText = viewShares.count.toString(),
                onItemClickListener = {
                    onShareClickListener(viewShares)
                }
            )
            val viewComments = statistics.getItemByType(StatisticType.COMMENTS)
            IconText(
                iconResource = R.drawable.ic_comment,
                descriptionImage = R.string.description_comment,
                countText = viewComments.count.toString(),
                onItemClickListener = {
                    onCommentClickListener(viewComments)
                }
            )
            val viewLikes = statistics.getItemByType(StatisticType.LIKES)
            IconText(
                iconResource = R.drawable.ic_like,
                descriptionImage = R.string.description_like,
                countText = viewLikes.count.toString(),
                onItemClickListener = {
                    onLikeClickListener(viewLikes)
                }
            )
        }
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
    onItemClickListener: () -> Unit
) {
    Row(
        modifier = Modifier.clickable {
            onItemClickListener()
        },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = iconResource),
            contentDescription = stringResource(descriptionImage),
            tint = MaterialTheme.colors.onSecondary
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = countText,
            color = MaterialTheme.colors.onSecondary
        )
    }
}
