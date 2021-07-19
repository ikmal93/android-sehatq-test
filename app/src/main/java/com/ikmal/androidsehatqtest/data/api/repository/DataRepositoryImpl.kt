package com.ikmal.androidsehatqtest.features.home.data.api.repository

import com.ikmal.androidsehatqtest.features.home.data.api.data.DataResponse
import com.ikmal.androidsehatqtest.features.home.data.api.service.ApiService

class DataRepositoryImpl(
    private val apiService: ApiService
) : DataRepository {
    override suspend fun getData(): List<DataResponse> {
        return apiService.getData()
    }
}