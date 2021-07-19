package com.ikmal.androidsehatqtest.data.api.service

import com.ikmal.androidsehatqtest.data.api.model.DataResponse
import retrofit2.http.GET

interface ApiService {
    @GET("home")
    suspend fun getData(): List<DataResponse>
}