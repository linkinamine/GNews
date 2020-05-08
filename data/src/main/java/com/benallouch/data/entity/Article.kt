package com.benallouch.data.entity

import java.util.*

data class Article(
        //We create some ids so we ca pass the to DiffUtil later
        val id: String = UUID.randomUUID().toString(),
        var source: NewsSource,
        var author: String,
        var title: String,
        var description: String,
        var url: String,
        var urlToImage: String?,
        var publishedAt: Date,
        var content: String)