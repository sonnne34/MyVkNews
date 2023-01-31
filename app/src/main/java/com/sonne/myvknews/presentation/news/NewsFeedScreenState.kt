package com.sonne.myvknews.presentation.news

import com.sonne.myvknews.domain.FeedPost

sealed class NewsFeedScreenState {

    object Initial : NewsFeedScreenState()

    object Loading: NewsFeedScreenState()

    data class Posts(
        val posts: List<FeedPost>,
        val nextDataIsLoading: Boolean = false,
    ) : NewsFeedScreenState()

}