package com.benallouch.data.repository

import com.benallouch.data.entity.Article
import com.benallouch.data.source.LocalDataSource
import com.benallouch.data.source.RemoteDataSource

class ArticlesRepository(
        private val localDataSource: LocalDataSource,
        private val remoteDataSource: RemoteDataSource,
        private val apiKey: String
) {

    suspend fun getArticles(): List<Article> {

        if (localDataSource.isEmpty()) {
            val articles =
                    remoteDataSource.getArticles(apiKey)
            localDataSource.saveArticles(articles)
        }

        return localDataSource.getArticles()
    }

    suspend fun findById(id: Int): Article = localDataSource.findById(id)

    suspend fun update(Article: Article) = localDataSource.update(Article)
}