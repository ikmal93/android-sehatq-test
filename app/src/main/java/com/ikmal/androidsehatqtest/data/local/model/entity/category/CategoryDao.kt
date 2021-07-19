package com.ikmal.androidsehatqtest.data.local.model.entity.category

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CategoryDao {
    @Query("SELECT * FROM category_entity")
    suspend fun getAll(): List<CategoryEntity>

    @Query("SELECT * FROM category_entity WHERE id = :id")
    suspend fun getCategory(id: Int?): CategoryEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(categories: List<CategoryEntity>)
}