package com.benallouch.data.source

import com.benallouch.data.entity.Article

interface LocalDataSource {
    suspend fun isEmpty(): Boolean
    suspend fun saveArticles(Articles: List<Article>)
    suspend fun getArticles(): List<Article>
    suspend fun findById(id: Int): Article
    suspend fun update(Article: Article)
}