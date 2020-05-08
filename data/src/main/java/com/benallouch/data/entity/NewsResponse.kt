package com.benallouch.data.entity

data class NewsResponse(var status: String, var totalResults: Int, var articles: ArrayList<Article>)