package com.ikmal.androidsehatqtest.core.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ikmal.androidsehatqtest.core.database.AppDatabase
import com.ikmal.androidsehatqtest.data.api.repository.DataRepositoryImpl
import com.ikmal.androidsehatqtest.data.api.service.ApiService
import com.ikmal.androidsehatqtest.data.local.repository.category.CategoryRepositoryImpl
import com.ikmal.androidsehatqtest.data.local.repository.history.HistoryRepositoryImpl
import com.ikmal.androidsehatqtest.data.local.repository.product.ProductPromoRepositoryImpl
import com.ikmal.androidsehatqtest.features.home.viewmodel.HomeViewModel
import com.ikmal.androidsehatqtest.features.product.search.viewmodel.ProductSearchViewModel
import com.ikmal.androidsehatqtest.features.profile.viewmodel.ProfileViewModel

class ViewModelFactory(private val apiService: ApiService, private val appDatabase: AppDatabase) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(
                DataRepositoryImpl(apiService),
                ProductPromoRepositoryImpl(appDatabase),
                CategoryRepositoryImpl(appDatabase)
            ) as T
        }
        if (modelClass.isAssignableFrom(ProductSearchViewModel::class.java)) {
            return ProductSearchViewModel(ProductPromoRepositoryImpl(appDatabase)) as T
        }
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            return ProfileViewModel(HistoryRepositoryImpl(appDatabase)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}