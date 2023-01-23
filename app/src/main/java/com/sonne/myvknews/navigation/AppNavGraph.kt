package com.sonne.myvknews.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sonne.myvknews.domain.FeedPost

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    profileScreenContent: @Composable () -> Unit,
    favouriteScreenContent: @Composable () -> Unit,
    feedNewsScreenContent: @Composable () -> Unit,
    commentsScreenContent: @Composable (FeedPost) -> Unit,

    ) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Home.route
    ) {
        homeScreenNavGraph(
            feedNewsScreenContent = feedNewsScreenContent,
            commentsScreenContent = commentsScreenContent
        )

        composable(Screen.Profile.route) {
            profileScreenContent()
        }
        composable(Screen.Favourite.route) {
            favouriteScreenContent()
        }
    }
}