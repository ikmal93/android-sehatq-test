package com.ikmal.androidsehatqtest.features.home.data.api.repository

import com.ikmal.androidsehatqtest.features.home.data.api.data.DataResponse


interface DataRepository {
    suspend fun getData(): List<DataResponse>
}