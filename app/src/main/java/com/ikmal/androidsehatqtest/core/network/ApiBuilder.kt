package com.ikmal.androidsehatqtest.core.network

import com.ikmal.androidsehatqtest.BuildConfig
import com.ikmal.androidsehatqtest.data.api.service.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiBuilder {
    private const val BASE_URL = "https://private-4639ce-ecommerce56.apiary-mock.com/"

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun httpClient(): OkHttpClient {
        val logger = HttpLoggingInterceptor()

        if (BuildConfig.DEBUG)
            logger.level = HttpLoggingInterceptor.Level.BODY
        else
            logger.level = HttpLoggingInterceptor.Level.NONE

        return OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()
    }

    val apiService: ApiService = getRetrofit().create(ApiService::class.java)
}