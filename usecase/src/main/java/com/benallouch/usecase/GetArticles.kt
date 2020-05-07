package com.benallouch.usecase

import com.benallouch.data.entity.Article
import com.benallouch.data.repository.ArticlesRepository

class GetArticles(private val moviesRepository: ArticlesRepository) {
    suspend fun invoke(): List<Article> = moviesRepository.getArticles()
}