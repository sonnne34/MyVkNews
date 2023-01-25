package com.sonne.myvknews.data.network

import com.sonne.myvknews.data.models.NewsFeedResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("newsfeed.getRecommended?v=5.131")
    suspend fun loadNews(
        @Query("access_token") token: String,
    ): NewsFeedResponseDto
}