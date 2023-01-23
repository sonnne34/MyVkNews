package com.sonne.myvknews.data.network

import com.sonne.myvknews.data.models.NewsFeesResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("newsfeed.get?v=5.131")
    suspend fun loadNews(
        @Query("access_token") token: String,
    ): NewsFeesResponseDto
}