package com.benallouch.yunar.di

import com.benallouch.data.repository.ArticlesRepository
import com.benallouch.yunar.ui.API_KEY
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataModule = module {
    factory { ArticlesRepository(get(), get(), get(named(API_KEY))) }
}