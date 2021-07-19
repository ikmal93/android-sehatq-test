package com.ikmal.androidsehatqtest.data.local.repository.category

import com.ikmal.androidsehatqtest.data.local.model.entity.category.CategoryEntity

interface CategoryRepository {
    suspend fun getAll(): List<CategoryEntity>
    suspend fun insertAll(categories: List<CategoryEntity>)
}