package com.ikmal.androidsehatqtest.data.local.repository.category

import com.ikmal.androidsehatqtest.core.database.AppDatabase
import com.ikmal.androidsehatqtest.data.local.model.entity.category.CategoryEntity

class CategoryRepositoryImpl(private val appDatabase: AppDatabase) : CategoryRepository {
    override suspend fun getAll(): List<CategoryEntity> {
        return appDatabase.categoryDao().getAll()
    }

    override suspend fun insertAll(categories: List<CategoryEntity>) {
        return appDatabase.categoryDao().insertAll(categories)
    }
}