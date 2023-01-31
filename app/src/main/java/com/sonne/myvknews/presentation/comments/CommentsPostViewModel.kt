package com.sonne.myvknews.presentation.comments

import android.app.Application
import androidx.lifecycle.*
import com.sonne.myvknews.data.repository.NewsFeedRepository
import com.sonne.myvknews.domain.FeedPost
import kotlinx.coroutines.launch

class CommentsPostViewModel(
    feedPost: FeedPost,
    application: Application,
) : AndroidViewModel(application) {

    private val repository = NewsFeedRepository(application)

    private val _stateComments =
        MutableLiveData<CommentsPostScreenState>(CommentsPostScreenState.Initial)
    val stateComments: LiveData<CommentsPostScreenState> = _stateComments

    init {
        loadCommentsPost(feedPost)
    }

    private fun loadCommentsPost(feedPost: FeedPost) {
        viewModelScope.launch {
            val comments = repository.getComments(feedPost)
            _stateComments.value = CommentsPostScreenState.Comments(
                feedPost = feedPost,
                comments = comments
            )
        }

    }
}