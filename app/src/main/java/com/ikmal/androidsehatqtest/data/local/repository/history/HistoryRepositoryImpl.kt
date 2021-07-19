package com.ikmal.androidsehatqtest.data.local.repository.history

import com.ikmal.androidsehatqtest.core.database.AppDatabase
import com.ikmal.androidsehatqtest.data.local.model.entity.history.HistoryEntity

class HistoryRepositoryImpl(private val appDatabase: AppDatabase) : HistoryRepository {
    override suspend fun getAll(): List<HistoryEntity> {
        return appDatabase.historyDao().getAll()
    }

    override suspend fun insertAll(histories: List<HistoryEntity>) {
        return appDatabase.historyDao().insertAll(histories)
    }

    override suspend fun insert(historyEntity: HistoryEntity) {
        return appDatabase.historyDao().insert(historyEntity)
    }
}