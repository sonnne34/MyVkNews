package com.sonne.myvknews.data.mapper

import android.util.Log
import com.sonne.myvknews.data.models.NewsFeedResponseDto
import com.sonne.myvknews.domain.FeedPost
import com.sonne.myvknews.domain.StatisticItem
import com.sonne.myvknews.domain.StatisticType
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.absoluteValue

class NewsFeedMapper {
    fun mapResponseToPosts(responseDto: NewsFeedResponseDto): List<FeedPost> {
        val result = mutableListOf<FeedPost>()

        val posts = responseDto.newsFeedContent.posts
        val groups = responseDto.newsFeedContent.groups

        for (post in posts) {
            val group = groups.find { it.id == post.communityId.absoluteValue } ?: break
            Log.d("mapResponseToPosts", "posts = ${post.text}")
            val feedPost = FeedPost(
                id = post.id,
                communityId = post.communityId,
                communityName = group.name,
                publicationDate = mapTimestampToDate(post.date * 1000),
                communityImageUrl = group.imgUrl,
                contentText = post.text,
                contentImageUrl = post.attachments?.firstOrNull()?.photo?.photoUrls?.lastOrNull()?.url,
                statistics = listOf(
                    StatisticItem(type = StatisticType.LIKES, post.likes.count),
                    StatisticItem(type = StatisticType.VIEWS, post.views.count),
                    StatisticItem(type = StatisticType.REPOSTS, post.reposts.count),
                    StatisticItem(type = StatisticType.COMMENTS, post.comments.count)
                ),
                isLiked = post.likes.userLikes > 0
            )
            result.add(feedPost)
        }
        return result
    }

    private fun mapTimestampToDate(timestamp: Long): String {
        val date = Date(timestamp)
        return SimpleDateFormat("d MMMM yyyy, hh:mm", Locale.getDefault()).format(date)
    }
}