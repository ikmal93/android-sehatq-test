package com.ikmal.androidsehatqtest.core.database

import androidx.room.Database
import com.ikmal.androidsehatqtest.data.local.model.entity.productpromo.ProductPromoDao
import com.ikmal.androidsehatqtest.data.local.model.entity.productpromo.ProductPromoEntity

@Database(entities = [ProductPromoEntity::class], version = 1)
abstract class Database {
    abstract fun productPromoDao(): ProductPromoDao
}