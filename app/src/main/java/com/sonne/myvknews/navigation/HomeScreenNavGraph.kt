package com.sonne.myvknews.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sonne.myvknews.domain.FeedPost

fun NavGraphBuilder.homeScreenNavGraph(
    feedNewsScreenContent: @Composable () -> Unit,
    commentsScreenContent: @Composable (FeedPost) -> Unit,
) {
    navigation(
        startDestination = Screen.ROUTE_NEWS_FEED,
        route = Screen.Home.route
    ) {
        composable(Screen.NewsFeed.route) {
            feedNewsScreenContent()
        }
        composable(Screen.Comments.route) {
            val feedPostId = it.arguments?.getInt(Screen.KEY_FEED_POST_ID) ?: 0
            commentsScreenContent(FeedPost(id = feedPostId))
        }
    }
}