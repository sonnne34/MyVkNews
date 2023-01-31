package com.sonne.myvknews.presentation.news

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sonne.myvknews.domain.FeedPost
import com.sonne.myvknews.ui.theme.ForestGreen

@Composable
fun NewsFeedScreen(
    paddingValues: PaddingValues,
    onCommentClickListener: (feedPost: FeedPost) -> Unit,
) {
    val viewModel: FeedPostViewModel = viewModel()
    val screenState = viewModel.screenState.observeAsState(NewsFeedScreenState.Initial)

    when (val currentState = screenState.value) {
        is NewsFeedScreenState.Posts -> {
            FeedPosts(
                viewModel = viewModel,
                paddingValues = paddingValues,
                posts = currentState.posts,
                onCommentClickListener = onCommentClickListener,
                nextDataIsLoading = currentState.nextDataIsLoading
            )
        }
        is NewsFeedScreenState.Initial -> {

        }
    }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
private fun FeedPosts(
    viewModel: FeedPostViewModel,
    paddingValues: PaddingValues,
    posts: List<FeedPost>,
    onCommentClickListener: (feedPost: FeedPost) -> Unit,
    nextDataIsLoading: Boolean,
) {
    LazyColumn(
        modifier = Modifier.padding(paddingValues),
        contentPadding = PaddingValues(
            top = 16.dp,
            start = 8.dp,
            end = 8.dp,
            bottom = 16.dp
        ),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            posts,
            key = { it.id }
        ) { feedPost ->

            val dismissState = rememberDismissState()
            if (dismissState.isDismissed(DismissDirection.EndToStart)) {
                viewModel.remove(feedPost)
            }

            SwipeToDismiss(
                modifier = Modifier.animateItemPlacement(),
                state = dismissState,
                directions = setOf(DismissDirection.EndToStart),
                background = {}
            ) {
                CardPost(
                    feedPost = feedPost,
                    onViewsClickListener = { statisticItem ->
                        viewModel.updateCount(feedPost, statisticItem)
                    },
                    onCommentClickListener = {
                        onCommentClickListener(feedPost)
                    },
                    onLikeClickListener = { _ ->
                        viewModel.changeLikeStatus(feedPost)
                    },
                    onShareClickListener = { statisticItem ->
                        viewModel.updateCount(feedPost, statisticItem)
                    }
                )
            }
        }
        item {
            if (nextDataIsLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = ForestGreen)
                }
            } else {
                SideEffect {
                    viewModel.loadNextRecommendations()
                }
            }
        }
    }
}