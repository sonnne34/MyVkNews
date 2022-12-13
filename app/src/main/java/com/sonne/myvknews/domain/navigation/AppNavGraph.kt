package com.sonne.myvknews.domain.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    homeScreenContent: @Composable () -> Unit,
    profileScreenContent: @Composable () -> Unit,
    favouriteScreenContent: @Composable () -> Unit,

){
    NavHost(
        navController = navHostController,
        startDestination = Screen.NewsFeed.route
    ){
        composable(Screen.NewsFeed.route) {
            homeScreenContent()
        }
        composable(Screen.Profile.route) {
            profileScreenContent()
        }
        composable(Screen.Favourite.route) {
            favouriteScreenContent()
        }
    }
}