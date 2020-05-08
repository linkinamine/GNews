package com.benallouch.data.source

import com.benallouch.data.entity.NewsResponse

interface RemoteDataSource {
    suspend fun getArticles(apiKey: String, page: Int): NewsResponse
}