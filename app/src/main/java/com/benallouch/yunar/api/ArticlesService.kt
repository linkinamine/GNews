package com.benallouch.yunar.api

import com.benallouch.data.entity.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticlesService {

    @GET("top-headlines?country=us")
    suspend fun getNewsHeadlines(
            @Query("apiKey") apiKey: String
    ): NewsResponse
}