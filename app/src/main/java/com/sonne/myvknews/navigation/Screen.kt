package com.sonne.myvknews.navigation

import com.sonne.myvknews.domain.FeedPost

sealed class Screen(
    val route: String,
) {

    object Home : Screen(route = ROUTE_HOME)
    object Profile : Screen(route = ROUTE_PROFILE)
    object Favourite : Screen(route = ROUTE_FAVOURITE)
    object NewsFeed : Screen(route = ROUTE_NEWS_FEED)

    object Comments : Screen(route = ROUTE_COMMENTS) {

        private const val ROUTE_FOR_ARGS = "comments"

        fun getRouteWithArgs(feedPost: FeedPost): String {
            return "$ROUTE_FOR_ARGS/${feedPost.id}"
        }

    }

    companion object {

        const val KEY_FEED_POST_ID = "feed_post_id"

        const val ROUTE_HOME = "home"
        const val ROUTE_PROFILE = "profile"
        const val ROUTE_FAVOURITE = "favorite"
        const val ROUTE_NEWS_FEED = "news_feed"
        const val ROUTE_COMMENTS = "comments/{$KEY_FEED_POST_ID}"
    }
}
