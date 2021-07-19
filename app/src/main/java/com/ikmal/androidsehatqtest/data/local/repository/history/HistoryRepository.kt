package com.ikmal.androidsehatqtest.data.local.repository.history

import com.ikmal.androidsehatqtest.data.local.model.entity.history.HistoryEntity

interface HistoryRepository {
    suspend fun getAll(): List<HistoryEntity>
    suspend fun insertAll(histories: List<HistoryEntity>)
    suspend fun insert(historyEntity: HistoryEntity)
}