package com.ikmal.androidsehatqtest.data.local.model.entity.productpromo

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProductPromoDao {
    @Query("SELECT * FROM product_promo_entity")
    suspend fun getAll(): List<ProductPromoEntity>

    @Query("SELECT * FROM product_promo_entity WHERE id = :id")
    suspend fun getProduct(id: String?): ProductPromoEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(products: List<ProductPromoEntity>)
}