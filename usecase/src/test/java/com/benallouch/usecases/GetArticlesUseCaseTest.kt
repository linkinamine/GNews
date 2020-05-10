package com.antonioleiva.usecases

import com.benallouch.data.entity.NewsResponse
import com.benallouch.data.repository.ArticlesRepository
import com.benallouch.usecase.GetArticlesUseCase
import com.benallouch.usecases.mockedArticle
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetArticlesUseCaseTest {

    @Mock
    lateinit var articlesRepository: ArticlesRepository

    lateinit var getPopularArticles: GetArticlesUseCase

    @Before
    fun setUp() {
        getPopularArticles = GetArticlesUseCase(articlesRepository)
    }

    @Test
    fun `invoke calls articles repository`() {
        runBlocking {

            val articles = arrayListOf(mockedArticle.copy(articleId = "id"))
            whenever(articlesRepository.getArticles(1,false)).thenReturn(NewsResponse(null, 100, articles))

            val result = getPopularArticles.invoke(1,false)

            Assert.assertEquals(articles, result.articles)
        }
    }
}