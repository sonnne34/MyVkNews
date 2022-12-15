package com.sonne.myvknews.ui

import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import com.sonne.myvknews.domain.FeedPost
import com.sonne.myvknews.domain.navigation.AppNavGraph
import com.sonne.myvknews.domain.navigation.Screen
import com.sonne.myvknews.domain.navigation.rememberNavigationState

@Composable
fun MyScreen() {
    val navigationState = rememberNavigationState()

    val commentsToPost: MutableState<FeedPost?> = remember {
        mutableStateOf(null)
    }

    Scaffold(
        bottomBar = {
            BottomNavigation {
                val navBackStEntry by navigationState.navHostController.currentBackStackEntryAsState()
                val currentRout = navBackStEntry?.destination?.route

                val items = listOf(
                    NavigationItem.Home,
                    NavigationItem.Favourite,
                    NavigationItem.Profile
                )
                items.forEach { item ->
                    BottomNavigationItem(
                        selected = currentRout == item.screen.route,
                        onClick = { navigationState.navigateTo(item.screen.route) },
                        icon = {
                            Icon(item.icon, contentDescription = null)
                        },
                        label = {
                            Text(text = stringResource(id = item.titleResId))
                        },
                        selectedContentColor = MaterialTheme.colors.onPrimary,
                        unselectedContentColor = MaterialTheme.colors.onSecondary
                    )
                }
            }
        }
    ) { paddingValues ->

        AppNavGraph(
            navHostController = navigationState.navHostController,
            feedNewsScreenContent = {
                HomeScreen(
                    paddingValues = paddingValues,
                    onCommentClickListener = {
                        commentsToPost.value = it
                        navigationState.navigateTo(Screen.Comments.route)
                    }
                )
            },
            commentsScreenContent = {
                CommentsPostScreen(
                    onBackPressed = { commentsToPost.value = null },
                    feedPost = commentsToPost.value!!
                )
            },
            profileScreenContent = { Text(text = "Profile", color = Color.Black) },
            favouriteScreenContent = { Text(text = "Favourite", color = Color.Black) })
    }
}