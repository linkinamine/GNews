package com.benallouch.yunar.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.benallouch.data.entity.NewsResponse
import com.benallouch.usecase.GetArticlesUseCase
import com.benallouch.yunar.ui.scope.ScopedViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class MainViewModel(private val getArticlesUseCase: GetArticlesUseCase,
                    uiDispatcher: CoroutineDispatcher) : ScopedViewModel(uiDispatcher) {


    private lateinit var fetchedData: NewsResponse
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

    fun onDataRequested(page: Int, fetchLocally: Boolean) {
        launch {
            if (page == 1)
                _articleModel.value = UiModel.Loading
            else
                _articleModel.value = UiModel.LoadingMore

            _articleModel.value = UiModel.Content(handleFetchedData(page, fetchLocally))
        }
    }

    private suspend fun handleFetchedData(page: Int, fetchLocally: Boolean): NewsResponse {
        if (!::fetchedData.isInitialized) {
            fetchedData = getArticlesUseCase.invoke(page, fetchLocally)
            return fetchedData
        }
        return getArticlesUseCase.invoke(page, fetchLocally) + fetchedData
    }

    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }

    sealed class UiModel {
        object Loading : UiModel()
        object LoadingMore : UiModel()
        data class Content(val newsResponse: NewsResponse) : UiModel()
        object RequestData : UiModel()
    }
}

private operator fun NewsResponse.plus(newsResponse: NewsResponse): NewsResponse {
    newsResponse.articles.addAll(this.articles)
    return newsResponse
}
