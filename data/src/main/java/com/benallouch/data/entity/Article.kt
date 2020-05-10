package com.benallouch.data.entity

import java.util.*

/**
 * Data class, we make most of the parameters as nullables since the api response gives different null values
 */
data class Article(
        //We create an id so we ca pass the to DiffUtil later
        var articleId: String,
        var source: NewsSource,
        var author: String?,
        var title: String?,
        var description: String?,
        var url: String?,
        var urlToImage: String?,
        var publishedAt: Date?,
        var content: String?)