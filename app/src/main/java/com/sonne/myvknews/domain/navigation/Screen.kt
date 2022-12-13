package com.sonne.myvknews.domain.navigation

sealed class Screen(
    val route: String,
) {

    object NewsFeed : Screen(route = ROUTE_NEWS_FEED)
    object Profile : Screen(route = ROUTE_PROFILE)
    object Favourite : Screen(route = ROUTE_FAVOURITE)

    companion object {
        const val ROUTE_NEWS_FEED = "news_feed"
        const val ROUTE_PROFILE = "profile"
        const val ROUTE_FAVOURITE = "favorite"
    }
}
