package com.benallouch.usecase

import com.benallouch.data.entity.Article
import com.benallouch.data.repository.ArticlesRepository


class FindArticleById(private val articlesRepository: ArticlesRepository) {
    suspend fun invoke(id: Int): Article = articlesRepository.findById(id)
}