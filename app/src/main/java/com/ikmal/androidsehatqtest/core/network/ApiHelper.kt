package com.ikmal.androidsehatqtest.data.api

class ApiHelper(private val apiService: ApiService) {
    suspend fun getUsers() = apiService.getData()
}