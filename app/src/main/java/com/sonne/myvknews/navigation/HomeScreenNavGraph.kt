package com.sonne.myvknews.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
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
        composable(
            route = Screen.Comments.route,
            arguments = listOf(
                navArgument(Screen.KEY_FEED_POST) {
                    type = FeedPost.NavigationType
                }
            )
        ) {
            val feedPost = it.arguments?.getParcelable<FeedPost>(Screen.KEY_FEED_POST)
                ?: throw RuntimeException("Args is null")

            commentsScreenContent(feedPost)
        }
    }
}