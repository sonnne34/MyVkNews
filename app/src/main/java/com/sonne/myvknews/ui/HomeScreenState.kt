package com.sonne.myvknews.ui

import com.sonne.myvknews.domain.CommentsPost
import com.sonne.myvknews.domain.FeedPost

sealed class HomeScreenState {

    object initial : HomeScreenState()

    data class Posts(val posts: List<FeedPost>) : HomeScreenState()

    data class Comments(val feedPost: FeedPost, val comments: List<CommentsPost>) :
        HomeScreenState()
}