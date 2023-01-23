package com.sonne.myvknews.data.mapper

import com.sonne.myvknews.data.models.NewsFeesResponseDto
import com.sonne.myvknews.domain.FeedPost
import com.sonne.myvknews.domain.StatisticItem
import com.sonne.myvknews.domain.StatisticType
import kotlin.math.absoluteValue

class NewsFeedMapper {

    fun mapResponseToPosts(responseDto: NewsFeesResponseDto): List<FeedPost> {
        val result = mutableListOf<FeedPost>()

        val posts = responseDto.newsFeedContent.posts
        val groups = responseDto.newsFeedContent.groups

        for (post in posts) {
            val group = groups.find { it.id == post.communityId.absoluteValue } ?: break
            val feedPost = FeedPost(
                id = post.id,
                communityName = group.name,
                publicationDate = post.date.toString(),
                communityImageUrl = group.imgUrl,
                contentText = post.text,
                contentImageUrl = post.attachments?.firstOrNull()?.photo?.photoUrls?.lastOrNull()?.url,
                statistics = listOf(
                    StatisticItem(type = StatisticType.LIKES, post.likes.count),
                    StatisticItem(type = StatisticType.VIEWS, post.views.count),
                    StatisticItem(type = StatisticType.REPOSTS, post.reposts.count),
                    StatisticItem(type = StatisticType.COMMENTS, post.comments.count)
                )
            )
            result.add(feedPost)
        }
        return result
    }
}