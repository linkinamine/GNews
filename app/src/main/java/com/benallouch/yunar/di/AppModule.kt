package com.benallouch.yunar.di

import com.benallouch.data.source.RemoteDataSource
import com.benallouch.yunar.R
import com.benallouch.yunar.api.ArticlesClient
import com.benallouch.yunar.api.RetroFitService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {
    //TODO Move api key and url to base config
    single(named("apiKey")) {androidApplication().getString(R.string.apiKey) }
    single(named("baseUrl")) { "https://newsapi.org/v2/" }

    //single { ArticleDatabase.build(get()) }
    //factory<LocalDataSource> { RoomDataSource(get()) }
    factory<RemoteDataSource> { ArticlesClient(get()) }
    single<CoroutineDispatcher> { Dispatchers.Main }
    single { RetroFitService(get(named("baseUrl"))) }
}