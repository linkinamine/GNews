package com.benallouch.yunar.db

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class ArticleDbEntity(
        @PrimaryKey(autoGenerate = true) val articleId: Int,
        @Embedded
        var source: NewsSourceDbEntity,
        var author: String?,
        var title: String,
        var description: String,
        var url: String,
        var urlToImage: String?,
        var publishedAt: Date,
        var content: String?
)

