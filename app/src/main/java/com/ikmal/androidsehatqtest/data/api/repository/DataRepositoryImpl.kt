package com.ikmal.androidsehatqtest.data.api.repository

import com.ikmal.androidsehatqtest.data.api.model.DataResponse
import com.ikmal.androidsehatqtest.data.api.service.ApiService

class DataRepositoryImpl(
    private val apiService: ApiService
) : DataRepository {
    override suspend fun getData(): List<DataResponse> {
        return apiService.getData()
    }
}