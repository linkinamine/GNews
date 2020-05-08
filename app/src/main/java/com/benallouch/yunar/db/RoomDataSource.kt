package com.benallouch.yunar.db

import com.benallouch.data.entity.Article
import com.benallouch.data.source.LocalDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RoomDataSource(db: ArticleRoomDatabase) : LocalDataSource {

    private val articleDao = db.articleDao()

    override suspend fun isEmpty(): Boolean =
            withContext(Dispatchers.IO) { articleDao.articleCount() <= 0 }

    override suspend fun saveArticles(articles: List<Article>) {
        withContext(Dispatchers.IO) { articleDao.insertArticles(articles.map { it.toArticleDatabase() }) }
    }

    override suspend fun saveTotalItems(totalItems: Int) {
        withContext(Dispatchers.IO) { articleDao.saveTotalItems(totalItems) }
    }

    override suspend fun getArticles(): List<Article> = withContext(Dispatchers.IO)
    {
        articleDao.getAll()
    }

    override suspend fun getTotalItems() = articleDao.getTotalItems()


    override suspend fun findById(id: String): Article = withContext(Dispatchers.IO) {
        articleDao.findById(id)
    }

    override suspend fun update(article: Article) {
        withContext(Dispatchers.IO) { articleDao.updateArticle(article.toArticleDatabase()) }
    }
}

private fun Article.toArticleDatabase() =
        ArticleDbEntity(id, source, author, title, description, url, urlToImage, publishedAt, content)


