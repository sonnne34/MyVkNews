package com.sonne.myvknews.domain.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation

fun NavGraphBuilder.homeScreenNavGraph(
    feedNewsScreenContent: @Composable () -> Unit,
    commentsScreenContent: @Composable () -> Unit,
) {
    navigation(
        startDestination = Screen.ROUTE_NEWS_FEED,
        route = Screen.Home.route
    ) {
        composable(Screen.NewsFeed.route) {
            feedNewsScreenContent()
        }
        composable(Screen.Comments.route) {
            commentsScreenContent()
        }
    }
}