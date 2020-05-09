package com.benallouch.data.source

import com.benallouch.data.entity.Article

interface LocalDataSource {
    suspend fun isEmpty(): Boolean
    suspend fun saveArticles(articles: List<Article>)
    suspend fun saveTotalItems(totalItems: Int)
    suspend fun getArticles(): List<Article>
    suspend fun getTotalItems(): Int
    suspend fun update(Article: Article)
}