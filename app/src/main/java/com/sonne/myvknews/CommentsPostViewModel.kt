package com.sonne.myvknews

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sonne.myvknews.domain.CommentsPost
import com.sonne.myvknews.domain.FeedPost
import com.sonne.myvknews.ui.CommentsPostScreenState

class CommentsPostViewModel(
    feedPost: FeedPost,
) : ViewModel() {

    private val _stateComments =
        MutableLiveData<CommentsPostScreenState>(CommentsPostScreenState.Initial)
    val stateComments: LiveData<CommentsPostScreenState> = _stateComments

    init {
        loadCommentsPost(feedPost)
    }

    private fun loadCommentsPost(feedPost: FeedPost) {
        val sourceList = mutableListOf<CommentsPost>().apply {
            repeat(10) {
                add(CommentsPost(id = it))
            }
        }
        _stateComments.value = CommentsPostScreenState.Comments(
            feedPost = feedPost,
            comments = sourceList
        )
    }
}