package com.ikmal.androidsehatqtest.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ikmal.androidsehatqtest.data.local.model.entity.category.CategoryDao
import com.ikmal.androidsehatqtest.data.local.model.entity.category.CategoryEntity
import com.ikmal.androidsehatqtest.data.local.model.entity.history.HistoryDao
import com.ikmal.androidsehatqtest.data.local.model.entity.history.HistoryEntity
import com.ikmal.androidsehatqtest.data.local.model.entity.productpromo.ProductPromoDao
import com.ikmal.androidsehatqtest.data.local.model.entity.productpromo.ProductPromoEntity

@Database(
    entities = [CategoryEntity::class, ProductPromoEntity::class, HistoryEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productPromoDao(): ProductPromoDao
    abstract fun categoryDao(): CategoryDao
    abstract fun historyDao(): HistoryDao
}