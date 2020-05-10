package com.benallouch.yunar.api

import com.benallouch.data.entity.NewsResponse
import com.benallouch.yunar.ui.API_KEY
import com.benallouch.yunar.ui.ITEMS_PER_FETCH
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticlesService {

    @GET("top-headlines?country=us")
    suspend fun getNewsHeadlines(
            @Query(API_KEY) apiKey: String, @Query("page") page: Int, @Query("pageSize") pageSie: Int = ITEMS_PER_FETCH
    ): NewsResponse
}