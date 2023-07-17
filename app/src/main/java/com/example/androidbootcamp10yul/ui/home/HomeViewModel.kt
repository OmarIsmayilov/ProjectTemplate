package com.example.androidbootcamp10yul.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidbootcamp10yul.api.StoreApi
import com.example.androidbootcamp10yul.base.NetworkResponseState
import com.example.androidbootcamp10yul.model.ProductResponseItem
import com.example.androidbootcamp10yul.repository.StoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val storeRepository: StoreRepository
) : ViewModel() {

    val data = MutableLiveData<List<ProductResponseItem>>()

    val loading = MutableLiveData<Boolean>()

    val error = MutableLiveData<String>()

    fun getProducts() {
        viewModelScope.launch {
            loading.value = true
            when (val response = storeRepository.getHomeData()) {
                is NetworkResponseState.Success -> {
                    data.value = response.result.body()
                    loading.value = false
                }

                is NetworkResponseState.Error -> {
                    error.value = response.exception.localizedMessage ?: "Error 404"
                    loading.value = false
                }
            }
        }
    }
}