package com.benallouch.usecases

import com.benallouch.data.entity.Article
import com.benallouch.data.entity.NewsSource
import java.time.Instant
import java.util.*

val mockedArticle = Article(
        "id",
        NewsSource(UUID.randomUUID().toString(), "ESPN"),
        "author",
        "title",
        "description",
        "url",
        "urlToImage",
        Date.from(Instant.now()),
        "content"
)