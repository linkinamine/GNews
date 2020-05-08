package com.benallouch.usecase

import com.benallouch.data.entity.NewsResponse
import com.benallouch.data.repository.ArticlesRepository

class GetArticlesUseCase(private val moviesRepository: ArticlesRepository) {
    suspend fun invoke(page: Int): NewsResponse = moviesRepository.getArticles(page)
}