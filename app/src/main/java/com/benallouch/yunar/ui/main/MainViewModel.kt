package com.benallouch.yunar.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.benallouch.data.entity.Article
import com.benallouch.usecase.GetArticlesUseCase
import com.benallouch.yunar.ui.scope.ScopedViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class MainViewModel(private val getArticlesUseCase: GetArticlesUseCase,
                    uiDispatcher: CoroutineDispatcher) : ScopedViewModel(uiDispatcher) {


    private val _articleModel = MutableLiveData<UiModel>()
    val articleModel: LiveData<UiModel>
        get() {
            if (_articleModel.value == null) refresh()
            return _articleModel
        }

    init {
        initScope()
    }

    private fun refresh() {
        _articleModel.value = UiModel.RequestData
    }

    fun onDataRequested() {
        launch {
            _articleModel.value = UiModel.Loading
            _articleModel.value = UiModel.Content(getArticlesUseCase.invoke())
        }
    }

    fun onMovieClicked(article: Article) {
        _articleModel.value = UiModel.Navigation(article)
    }

    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }

    sealed class UiModel {
        object Loading : UiModel()
        data class Content(val articles: List<Article>) : UiModel()
        data class Navigation(val article: Article) : UiModel()
        object RequestData : UiModel()
    }
}
