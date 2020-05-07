package com.benallouch.yunar.api

import com.benallouch.data.entity.Article
import com.benallouch.data.source.RemoteDataSource

class ArticlesClient(private val retroFitService: RetroFitService) : RemoteDataSource {
    override suspend fun getArticles(apiKey: String): List<Article> =
            retroFitService.service.getNewsHeadlines(apiKey).articles

}

