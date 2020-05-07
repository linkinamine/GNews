package com.benallouch.yunar.di

import com.benallouch.usecase.GetArticlesUseCase
import com.benallouch.yunar.ui.main.MainActivity
import com.benallouch.yunar.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val scopesModule = module {
    scope(named<MainActivity>()) {
        viewModel { MainViewModel(get(), get()) }
        scoped { GetArticlesUseCase(get()) }
    }

}