package com.benallouch.data.repository

import com.benallouch.data.entity.Article
import com.benallouch.data.entity.NewsResponse
import com.benallouch.data.source.LocalDataSource
import com.benallouch.data.source.RemoteDataSource
import java.util.*


class ArticlesRepository(
        private val localDataSource: LocalDataSource,
        private val remoteDataSource: RemoteDataSource,
        private val apiKey: String
) {

    suspend fun getArticles(page: Int): NewsResponse {

        if (localDataSource.isEmpty()) {
            val newsResponse = remoteDataSource.getArticles(apiKey, page)
            localDataSource.saveArticles(newsResponse.articles)
            localDataSource.saveTotalItems(newsResponse.totalResults)

        }
        return NewsResponse(null, localDataSource.getTotalItems(), localDataSource.getArticles() as ArrayList<Article>)

    }

}