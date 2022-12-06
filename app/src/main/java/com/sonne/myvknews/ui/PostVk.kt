package com.sonne.myvknews.ui

import androidx.compose.foundation.Image
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sonne.myvknews.R
import com.sonne.myvknews.ui.theme.MyVkNewsTheme

@Composable
fun CardPost() {
    Card {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            HeaderPost()
            Spacer(modifier = Modifier.height(8.dp))
            ContentPost()
            Spacer(modifier = Modifier.height(8.dp))
            InfoPost()
        }
    }
}

@Composable
private fun HeaderPost() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape),
            painter = painterResource(id = R.drawable.post_comunity_thumbnail),
            contentDescription = stringResource(R.string.description_avatar)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = "копатыч")
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "14:55",
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
private fun ContentPost() {
    Text(text = "Очень интересный текст со всяким там ИТ-юмором, жи есть.")
    Spacer(modifier = Modifier.height(8.dp))
    Image(
        modifier = Modifier.fillMaxWidth(),
        painter = painterResource(id = R.drawable.post_content_image),
        contentScale = ContentScale.FillWidth,
        contentDescription = stringResource(R.string.description_content)
    )
}

@Composable
private fun InfoPost() {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.weight(1f)
        ) {
            IconText(
                iconResource = R.drawable.ic_views_count,
                descriptionImage = R.string.description_views,
                countText = "958"
            )
        }
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            IconText(
                iconResource = R.drawable.ic_share,
                descriptionImage = R.string.description_share,
                countText = "958"
            )
            IconText(
                iconResource = R.drawable.ic_comment,
                descriptionImage = R.string.description_comment,
                countText = "15"
            )
            IconText(
                iconResource = R.drawable.ic_like,
                descriptionImage = R.string.description_like,
                countText = "157"
            )
        }

    }
}

@Composable
private fun IconText(
    iconResource: Int,
    descriptionImage: Int,
    countText: String
) {
    Row {
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

@Preview
@Composable
private fun PreviewDarkThemePost() {
    MyVkNewsTheme(darkTheme = true) {
        CardPost()
    }
}

@Preview
@Composable
private fun PreviewLightThemePost() {
    MyVkNewsTheme(darkTheme = false) {
        CardPost()
    }
}
