package com.benallouch.yunar.db

import com.benallouch.data.entity.Article
import com.benallouch.data.entity.NewsSource
import com.benallouch.yunar.db.entity.ArticleDbEntity
import com.benallouch.yunar.db.entity.NewsSourceDbEntity
import java.util.*

fun ArticleDbEntity.toArticleEntity() = Article(
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

fun Article.toArticleDbEntity(): ArticleDbEntity =
        ArticleDbEntity(
                articleId,
                NewsSourceDbEntity(source.id
                        ?: UUID.randomUUID().toString(), source.name),
                author,
                title,
                description,
                url,
                urlToImage,
                publishedAt,
                content
        )
