package com.ikmal.androidsehatqtest.features.product.search.viewmodel

import androidx.lifecycle.*
import com.ikmal.androidsehatqtest.core.utils.Resource
import com.ikmal.androidsehatqtest.data.api.model.ProductPromo
import com.ikmal.androidsehatqtest.data.api.repository.DataRepository
import com.ikmal.androidsehatqtest.data.local.repository.product.ProductPromoRepository
import kotlinx.coroutines.launch

class ProductSearchViewModel(
    private val productPromoRepository: ProductPromoRepository
) : ViewModel() {

    private val promoProducts = MutableLiveData<Resource<List<ProductPromo>>>()

    private fun fetchProductPromo() {
        viewModelScope.launch {
            promoProducts.postValue(Resource.loading(null))
            try {

            } catch (e: Exception) {
            }
        }
    }

    fun getPromoProducts(): LiveData<Resource<List<ProductPromo>>> {
        return promoProducts
    }
}