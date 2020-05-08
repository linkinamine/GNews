package com.benallouch.data.repository

import com.benallouch.data.entity.Article
import com.benallouch.data.entity.NewsResponse
import com.benallouch.data.entity.NewsSource
import com.benallouch.data.source.RemoteDataSource
import java.util.*

//TODO Enable room once available

class ArticlesRepository(
        //private val localDataSource: LocalDataSource,
        private val remoteDataSource: RemoteDataSource,
        private val apiKey: String
) {

    suspend fun getArticles(page: Int):NewsResponse {

        /*if (localDataSource.isEmpty()) {
            val articles =
                    remoteDataSource.getArticles(apiKey)
            localDataSource.saveArticles(articles)
        }

        return localDataSource.getArticles()*/

        return remoteDataSource.getArticles(apiKey, page)
    }

    // suspend fun findById(id: Int): Article = localDataSource.findById(id)
    suspend fun findById(id: Int): Article = Article("id", NewsSource("", ""), "", "", "", "", "", Date(), "")
}