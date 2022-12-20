package com.sonne.myvknews.presentation.news

import com.sonne.myvknews.domain.FeedPost

sealed class NewsFeedScreenState {

    object Initial : NewsFeedScreenState()

    data class Posts(val posts: List<FeedPost>) : NewsFeedScreenState()

}