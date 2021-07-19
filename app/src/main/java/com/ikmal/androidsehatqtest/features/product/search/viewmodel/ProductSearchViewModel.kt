package com.ikmal.androidsehatqtest.features.product.search.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ikmal.androidsehatqtest.core.utils.Resource
import com.ikmal.androidsehatqtest.data.local.model.entity.productpromo.ProductPromoEntity
import com.ikmal.androidsehatqtest.data.local.repository.product.ProductPromoRepository
import kotlinx.coroutines.launch

class ProductSearchViewModel(
    private val productPromoRepository: ProductPromoRepository
) : ViewModel() {

    private val promoProducts = MutableLiveData<Resource<List<ProductPromoEntity>>>()

    private val filteredPromoProducts = MutableLiveData<Resource<List<ProductPromoEntity>>>()
    private val filterKeyword = MutableLiveData("")

    init {
        fetchProductPromo()
    }

    private fun fetchProductPromo() {
        viewModelScope.launch {
            promoProducts.postValue(Resource.loading(null))
            try {
                promoProducts.postValue(Resource.success(productPromoRepository.getAll()))
            } catch (e: Exception) {
                promoProducts.postValue(Resource.error(null, "Something went wrong"))
            }
        }
    }

    fun filterProduct() {
        viewModelScope.launch {
            filteredPromoProducts.postValue(Resource.loading(null))
            try {
                refreshProduct()
            } catch (e: Exception) {
                filteredPromoProducts.postValue(Resource.error(null, "Something went wrong"))
            }
        }
    }

    private fun refreshProduct() {
        val filterKeywordValue = filterKeyword.value ?: ""
        val filteredPromo = promoProducts.value?.data?.let {
            it.filter { data ->
                data.title.lowercase().contains(filterKeywordValue.lowercase())
            }
        }
        filteredPromoProducts.postValue(Resource.success(filteredPromo!!))
    }

    fun getPromoProducts(): LiveData<Resource<List<ProductPromoEntity>>> {
        return promoProducts
    }

    fun getFilterProducts(): LiveData<Resource<List<ProductPromoEntity>>> {
        return filteredPromoProducts
    }

    fun setFilterKeyword(filterKeyword: String) {
        this.filterKeyword.value = filterKeyword
        filterProduct()
//        refreshProduct()
    }
}