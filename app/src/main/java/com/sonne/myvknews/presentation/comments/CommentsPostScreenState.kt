package com.sonne.myvknews.presentation.comments

import com.sonne.myvknews.domain.CommentsPost
import com.sonne.myvknews.domain.FeedPost

sealed class CommentsPostScreenState {

    object Initial : CommentsPostScreenState()

    data class Comments(
        val feedPost: FeedPost,
        val comments: List<CommentsPost>,
    ) :
        CommentsPostScreenState()

}