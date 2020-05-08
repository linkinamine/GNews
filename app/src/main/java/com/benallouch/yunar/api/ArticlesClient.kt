package com.benallouch.yunar.api

import com.benallouch.data.entity.NewsResponse
import com.benallouch.data.source.RemoteDataSource

class ArticlesClient(private val retroFitService: RetroFitService) : RemoteDataSource {
    override suspend fun getArticles(apiKey: String,page:Int): NewsResponse =
            retroFitService.service.getNewsHeadlines(apiKey,page)

}

