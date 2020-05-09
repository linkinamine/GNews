package com.benallouch.yunar.api

import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroFitService(baseUrl: String) {

    private val okHttpClient = HttpLoggingInterceptor().run {
        OkHttpClient.Builder().addNetworkInterceptor(StethoInterceptor())
                .build()
    }

    val service: ArticlesService = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .run { create(ArticlesService::class.java) }
}
