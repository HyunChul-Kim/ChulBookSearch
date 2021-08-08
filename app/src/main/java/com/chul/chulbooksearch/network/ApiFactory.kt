package com.chul.chulbooksearch.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {
    private const val baseUrl = "https://openapi.naver.com/v1/search/"
    private const val clientId = "HbF17_cXrVzo9LmOy5cF"
    private const val clientSecret = "8HpzMNygQP"

    val bookService: BookService =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder()
                .addInterceptor {
                    val request = it.request()
                    val authRequest = request.newBuilder()
                        .addHeader("X-Naver-Client-Id", clientId)
                        .addHeader("X-Naver-Client-Secret", clientSecret)
                        .build()
                    it.proceed(authRequest)
                }
                .addInterceptor(HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY) })
                .build())
            .build()
            .create(BookService::class.java)
}