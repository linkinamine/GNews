package com.benallouch.data.entity

data class Article(
        var source: NewsSource,
        var author: String,
        var title: String,
        var description: String,
        var url: String,
        var urlToImage: String,
        var publishedAt: String,
        var content: String)