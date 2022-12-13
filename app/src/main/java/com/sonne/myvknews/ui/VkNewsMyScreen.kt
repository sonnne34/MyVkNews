package com.sonne.myvknews.ui

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.sonne.myvknews.MainViewModel
import com.sonne.myvknews.domain.navigation.AppNavGraph
import com.sonne.myvknews.domain.navigation.Screen

@Composable
fun MyScreen(viewModel: MainViewModel) {
    val navHostController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigation {
                val navBackStEntry by navHostController.currentBackStackEntryAsState()
                val currentRout = navBackStEntry?.destination?.route

                val items = listOf(
                    NavigationItem.Home,
                    NavigationItem.Favourite,
                    NavigationItem.Profile
                )
                items.forEach { item ->
                    BottomNavigationItem(
                        selected = currentRout == item.screen.route,
                        onClick = { navHostController.navigate(item.screen.route){
                            popUpTo(Screen.NewsFeed.route){
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        } },
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

        AppNavGraph(navHostController = navHostController,
            homeScreenContent = {
                HomeScreen(viewModel = viewModel,
                    paddingValues = paddingValues)
            },
            profileScreenContent = { Text(text = "Profile", color = Color.Black) },
            favouriteScreenContent = { Text(text = "Favourite", color = Color.Black) })
    }
}