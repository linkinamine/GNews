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

    fun onDataRequested(page: Int) {
        launch {
            if (page == 1)
                _articleModel.value = UiModel.Loading
            else
                _articleModel.value = UiModel.LoadingMore

            _articleModel.value = UiModel.Content(getArticlesUseCase.invoke(page))
        }
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
