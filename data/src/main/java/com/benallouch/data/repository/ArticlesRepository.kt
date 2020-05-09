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

    suspend fun getArticles(page: Int, shouldFetchRemotely: Boolean): NewsResponse {

        var newsResponse: NewsResponse

        when {
            shouldFetchRemotely -> {
                newsResponse = remoteDataSource.getArticles(apiKey, page)
                localDataSource.saveArticles(newsResponse.articles)
                localDataSource.saveTotalItems(newsResponse.totalResults)
            }
            else -> {
                if (!localDataSource.isEmpty())
                    newsResponse = NewsResponse(null, localDataSource.getTotalItems(), localDataSource.getArticles() as ArrayList<Article>)
                else throw IllegalAccessException("Trying to access an empty database!")

            }
        }
        return newsResponse

    }

}