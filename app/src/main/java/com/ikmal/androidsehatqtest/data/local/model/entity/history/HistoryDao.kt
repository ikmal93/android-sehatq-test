package com.ikmal.androidsehatqtest.data.local.model.entity.history

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HistoryDao {
    @Query("SELECT * FROM history_entity")
    suspend fun getAll(): List<HistoryEntity>

    @Query("SELECT * FROM history_entity WHERE id = :id")
    suspend fun getHistory(id: String?): HistoryEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(histories: List<HistoryEntity>)

    @Insert
    suspend fun insert(historyEntity: HistoryEntity)
}