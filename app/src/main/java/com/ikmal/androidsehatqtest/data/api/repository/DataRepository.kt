package com.ikmal.androidsehatqtest.data.api.repository

import com.ikmal.androidsehatqtest.data.api.model.DataResponse


interface DataRepository {
    suspend fun getData(): List<DataResponse>
}