package com.ikmal.androidsehatqtest.features.home.viewmodel

import androidx.lifecycle.*
import com.ikmal.androidsehatqtest.core.utils.Resource
import com.ikmal.androidsehatqtest.data.api.model.Category
import com.ikmal.androidsehatqtest.data.api.model.DataResponse
import com.ikmal.androidsehatqtest.data.api.model.ProductPromo
import com.ikmal.androidsehatqtest.data.api.repository.DataRepository
import com.ikmal.androidsehatqtest.data.local.model.entity.category.CategoryEntity
import com.ikmal.androidsehatqtest.data.local.model.entity.productpromo.ProductPromoEntity
import com.ikmal.androidsehatqtest.data.local.repository.category.CategoryRepository
import com.ikmal.androidsehatqtest.data.local.repository.product.ProductPromoRepository
import kotlinx.coroutines.launch

class HomeViewModel(
    private val dataRepository: DataRepository,
    private val productPromoRepository: ProductPromoRepository,
    private val categoryRepository: CategoryRepository
) : ViewModel() {

    private val data = MutableLiveData<Resource<List<DataResponse>>>()

    init {
        fetchDataRepository()
    }

    private fun fetchDataRepository() {
        viewModelScope.launch {
            data.postValue(Resource.loading(null))
            try {
                val dataFromApi = dataRepository.getData()
                if (productPromoRepository.getAll().isEmpty()) {
                    val productPromoToInsertInDB = dataFromApi[0].data.productPromo.map {
                        ProductPromoEntity(
                            id = it.id,
                            description = it.description,
                            imageUrl = it.imageUrl,
                            loved = it.loved,
                            price = it.price,
                            title = it.title
                        )
                    }
                    productPromoRepository.insertAll(productPromoToInsertInDB)
                }
                if (categoryRepository.getAll().isEmpty()) {
                    val categoryToInsertInDB = dataFromApi[0].data.category.map {
                        CategoryEntity(
                            id = it.id,
                            imageUrl = it.imageUrl,
                            name = it.name
                        )
                    }
                    categoryRepository.insertAll(categoryToInsertInDB)
                }
                data.postValue(Resource.success(dataFromApi))
            } catch (e: Exception) {
                data.postValue(Resource.error(null, "Something Went Wrong"))
            }
        }
    }

    fun getData(): LiveData<Resource<List<DataResponse>>> {
        return data
    }
}