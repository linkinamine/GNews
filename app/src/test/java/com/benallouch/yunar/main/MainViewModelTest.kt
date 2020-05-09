package com.benallouch.yunar.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.benallouch.data.entity.NewsResponse
import com.benallouch.usecase.GetArticlesUseCase
import com.benallouch.yunar.mockedArticle
import com.benallouch.yunar.ui.main.MainViewModel
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var getArticles: GetArticlesUseCase

    @Mock
    lateinit var observer: Observer<MainViewModel.UiModel>

    private lateinit var vm: MainViewModel

    @Before
    fun setUp() {
        vm = MainViewModel(getArticles, Dispatchers.Unconfined)
    }


    @Test
    fun `show loading first`() {
        runBlocking {

            val articles = arrayListOf(mockedArticle.copy(articleId = 1))
            whenever(getArticles.invoke(1,false)).thenReturn(NewsResponse(null, 100, articles ))
            vm.articleModel.observeForever(observer)

            vm.onDataRequested(1,false)

            verify(observer).onChanged(MainViewModel.UiModel.Loading)
        }
    }

    @Test
    fun `getArticles is called after loading`() {

        runBlocking {
            val articles = arrayListOf(mockedArticle.copy(articleId = 1))

            whenever(getArticles.invoke(1,false)).thenReturn(NewsResponse(null, 100,articles))
            vm.articleModel.observeForever(observer)

            vm.onDataRequested(1,false)

            verify(observer).onChanged(MainViewModel.UiModel.Content(NewsResponse(null, 100, articles)))
        }
    }
}