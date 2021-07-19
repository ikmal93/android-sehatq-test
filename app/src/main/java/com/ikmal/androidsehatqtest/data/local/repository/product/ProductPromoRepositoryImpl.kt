package com.ikmal.androidsehatqtest.data.local.repository.product

import com.ikmal.androidsehatqtest.core.database.AppDatabase
import com.ikmal.androidsehatqtest.data.local.model.entity.productpromo.ProductPromoEntity

class ProductPromoRepositoryImpl(private val appDatabase: AppDatabase) : ProductPromoRepository {
    override suspend fun getAll(): List<ProductPromoEntity> {
        return appDatabase.productPromoDao().getAll()
    }

    override suspend fun insertAll(products: List<ProductPromoEntity>) {
        return appDatabase.productPromoDao().insertAll(products)
    }
}