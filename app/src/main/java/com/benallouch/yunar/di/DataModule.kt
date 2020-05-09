package com.benallouch.yunar.di

import com.benallouch.data.repository.ArticlesRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataModule = module {
    factory { ArticlesRepository(get(), get(), get(named("apiKey"))) }
}