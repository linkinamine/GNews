package com.benallouch.yunar.db.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class ArticleDbEntity(
        @PrimaryKey val articleId: String,
        @Embedded
        var source: NewsSourceDbEntity,
        var author: String?,
        var title: String?,
        var description: String?,
        var url: String?,
        var urlToImage: String?,
        var publishedAt: Date?,
        var content: String?
)

