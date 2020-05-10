package com.benallouch.yunar.db

import com.benallouch.data.entity.Article
import com.benallouch.data.source.LocalDataSource
import com.benallouch.yunar.db.entity.TotalResultsEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class RoomDataSource(db: ArticleRoomDatabase) : LocalDataSource {

    private val articleDao = db.articleDao()

    override suspend fun isEmpty(): Boolean =
            withContext(Dispatchers.IO) { articleDao.articleCount() <= 0 }

    override suspend fun saveArticles(articles: List<Article>) {
        withContext(Dispatchers.IO) { articleDao.insertArticles(articles.map { it.toArticleDbEntity() }) }
    }

    override suspend fun saveTotalItems(totalItems: Int) {
        withContext(Dispatchers.IO) { articleDao.saveTotalItems(TotalResultsEntity(UUID.randomUUID().toString(), totalItems)) }
    }

    override suspend fun getArticles(): List<Article> = withContext(Dispatchers.IO)
    {
        articleDao.getAll().map { it.toArticleEntity() }
    }

    override suspend fun getTotalItems() = withContext(Dispatchers.IO) { articleDao.getTotalItems() }

    override suspend fun update(article: Article) {
        withContext(Dispatchers.IO) { articleDao.updateArticle(article.toArticleDbEntity()) }
    }
}

