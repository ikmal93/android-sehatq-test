package com.ikmal.androidsehatqtest.features.product.search.viewmodel

import androidx.lifecycle.*
import com.ikmal.androidsehatqtest.core.utils.Resource
import com.ikmal.androidsehatqtest.data.api.model.ProductPromo
import com.ikmal.androidsehatqtest.data.api.repository.DataRepository
import com.ikmal.androidsehatqtest.data.local.repository.product.ProductPromoRepository

class ProductSearchViewModel(
    private val dataRepository: DataRepository,
    private val productPromoRepository: ProductPromoRepository
) : ViewModel() {

    private val promoProducts = MutableLiveData<Resource<List<ProductPromo>>>()

    init {
//        fetchProductPromo()
    }

//    private fun fetchProductPromo() {
//        viewModelScope.launch {
//            promoProducts.postValue(Resource.loading(null))
//            try {
//                val usersFromDb = productPromoRepository.getAll()
//                if (usersFromDb.isEmpty()) {
//                    val productPromoFromApi = dataRepository.getData()
//                    val productPromoToInsertInDB = productPromoFromApi[0].data.productPromo.map {
//                        ProductPromoEntity(
//                            id = it.id,
//                            description = it.description,
//                            imageUrl = it.imageUrl,
//                            loved = it.loved,
//                            price = it.price,
//                            title = it.title
//                        )
//                    }
//                    productPromoRepository.insertAll(productPromoToInsertInDB)
//                    promoProducts.postValue(Resource.success(ProductPromo.toProductPromoEntity(productPromoToInsertInDB)))
//                } else {
//                    promoProducts.postValue(Resource.success(usersFromDb))
//                }
//            } catch (e: Exception) {
//                promoProducts.postValue(Resource.error("Something Went Wrong", null))
//            }
//        }
//    }

    fun getPromoProducts(): LiveData<Resource<List<ProductPromo>>> {
        return promoProducts
    }
}