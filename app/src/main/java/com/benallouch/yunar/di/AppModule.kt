package com.benallouch.yunar.di

import com.benallouch.data.source.LocalDataSource
import com.benallouch.data.source.RemoteDataSource
import com.benallouch.yunar.BuildConfig
import com.benallouch.yunar.R
import com.benallouch.yunar.api.ArticlesClient
import com.benallouch.yunar.api.RetroFitService
import com.benallouch.yunar.db.ArticleRoomDatabase
import com.benallouch.yunar.db.RoomDataSource
import com.benallouch.yunar.ui.API_KEY
import com.benallouch.yunar.ui.BASE_URL
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {
    //NETWORK
    single(named(API_KEY)) { androidApplication().getString(R.string.API_KEY) }
    single(named(BASE_URL)) { BuildConfig.BASE_URL }
    single { RetroFitService(get(named(BASE_URL))) }

    //APP
    single { ArticleRoomDatabase.build(get()) }
    factory<LocalDataSource> { RoomDataSource(get()) }
    factory<RemoteDataSource> { ArticlesClient(get()) }
    single<CoroutineDispatcher> { Dispatchers.Main }
}