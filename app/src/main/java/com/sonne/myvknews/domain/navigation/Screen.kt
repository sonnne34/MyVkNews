package com.sonne.myvknews.domain.navigation

sealed class Screen(
    val route: String,
) {

    object Home : Screen(route = ROUTE_HOME)
    object Profile : Screen(route = ROUTE_PROFILE)
    object Favourite : Screen(route = ROUTE_FAVOURITE)
    object NewsFeed : Screen(route = ROUTE_NEWS_FEED)
    object Comments : Screen(route = ROUTE_COMMENTS)

    companion object {
        const val ROUTE_HOME = "home"
        const val ROUTE_PROFILE = "profile"
        const val ROUTE_FAVOURITE = "favorite"
        const val ROUTE_NEWS_FEED = "news_feed"
        const val ROUTE_COMMENTS = "comments"
    }
}
