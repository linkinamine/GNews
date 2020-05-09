package com.benallouch.yunar.db

import com.benallouch.data.entity.Article
import com.benallouch.data.entity.NewsSource
import com.benallouch.data.source.LocalDataSource
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

private fun ArticleDbEntity.toArticleEntity() = Article(
        articleId,
        NewsSource(source.newsSourceId, source.name),
        author,
        title,
        description,
        url,
        urlToImage,
        publishedAt,
        content
)

private fun Article.toArticleDbEntity(): ArticleDbEntity =
        ArticleDbEntity(
                articleId,
                NewsSourceDbEntity(source.id ?: UUID.randomUUID().toString(), source.name),
                author,
                title,
                description,
                url,
                urlToImage,
                publishedAt,
                content
        )

