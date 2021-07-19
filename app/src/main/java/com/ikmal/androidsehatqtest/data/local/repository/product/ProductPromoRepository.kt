package com.ikmal.androidsehatqtest.data.local.repository

import com.ikmal.androidsehatqtest.data.local.model.entity.productpromo.ProductPromoEntity

interface ProductPromoRepository {
    suspend fun getAll(): List<ProductPromoEntity>
    suspend fun insertAll(products: List<ProductPromoEntity>)
}