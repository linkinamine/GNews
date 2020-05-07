package com.benallouch.data.source

import com.benallouch.data.entity.Article

interface RemoteDataSource {
    suspend fun getArticles(apiKey: String): List<Article>
}