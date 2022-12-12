package com.sonne.myvknews.domain

import com.sonne.myvknews.R

data class FeedPost(
    val id: Int = 0,
    val communityName: String = "копатыч",
    val dataPost: String = "14:00",
    val avatarResId: Int = R.drawable.post_comunity_thumbnail,
    val contentText: String = "Очень интересный текст со всяким там ИТ-юмором, жи есть.",
    val contentImageResId: Int = R.drawable.post_content_image,
    val statistics: List<StatisticItem> = listOf(
        StatisticItem(type = StatisticType.VIEWS, 966),
        StatisticItem(type = StatisticType.SHARES, 17),
        StatisticItem(type = StatisticType.COMMENTS, 25),
        StatisticItem(type = StatisticType.LIKES, 589)
    )
)
