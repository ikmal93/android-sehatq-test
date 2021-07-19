package com.ikmal.androidsehatqtest.core.network

import com.ikmal.androidsehatqtest.data.api.service.ApiService

class ApiHelper(private val apiService: ApiService) {
    suspend fun getUsers() = apiService.getData()
}