package com.ikmal.androidsehatqtest.features.profile.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ikmal.androidsehatqtest.core.utils.Resource
import com.ikmal.androidsehatqtest.data.local.model.entity.history.HistoryEntity
import com.ikmal.androidsehatqtest.data.local.model.entity.productpromo.ProductPromoEntity
import com.ikmal.androidsehatqtest.data.local.repository.history.HistoryRepository
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val historyRepository: HistoryRepository,
) : ViewModel() {

    private val histories = MutableLiveData<Resource<List<HistoryEntity>>>()
    private val isDataInserted = MutableLiveData<Resource<Boolean>>()

    init {
        fetchHistories()
    }

    private fun fetchHistories() {
        viewModelScope.launch {
            histories.postValue(Resource.loading(null))
            try {
                histories.postValue(Resource.success(historyRepository.getAll()))
            } catch (e: Exception) {
                histories.postValue(Resource.error(null, "Something Went Wrong"))
            }
        }
    }

    fun insertHistories(historyEntity: HistoryEntity) {
        viewModelScope.launch {
            isDataInserted.postValue(Resource.loading(null))
            try {
                historyRepository.insert(historyEntity)
                isDataInserted.postValue(Resource.success(true))
            } catch (e: Exception) {
                isDataInserted.postValue(Resource.error(false, "Something Went Wrong"))
            }
        }
    }

    fun getHistories(): LiveData<Resource<List<HistoryEntity>>> {
        return histories
    }

    fun postHistories(): LiveData<Resource<Boolean>> {
        return isDataInserted
    }
}