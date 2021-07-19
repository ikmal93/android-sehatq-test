package com.ikmal.androidsehatqtest.features.home.data.api.service

import com.ikmal.androidsehatqtest.features.home.data.api.data.DataResponse
import retrofit2.http.GET

interface ApiService {
    @GET("home")
    suspend fun getData(): List<DataResponse>
}