package com.sonne.myvknews.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sonne.myvknews.R
import com.sonne.myvknews.ui.theme.MyVkNewsTheme

@Composable
fun CardPost() {
    Card(
        shape = RoundedCornerShape(4.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape),
                painter = painterResource(id = R.drawable.post_comunity_thumbnail),
                contentDescription = "Avatar"
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "копатыч")
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "14:55",
                    color = MaterialTheme.colors.onSecondary
                )
            }
            Icon(
                imageVector = Icons.Rounded.MoreVert,
                tint = MaterialTheme.colors.onSecondary,
                contentDescription = "More"
            )
        }
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
