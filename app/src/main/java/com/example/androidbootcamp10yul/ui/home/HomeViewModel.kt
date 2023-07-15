package com.example.androidbootcamp10yul.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidbootcamp10yul.api.StoreApi
import com.example.androidbootcamp10yul.model.ProductResponseItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val api: StoreApi
) : ViewModel() {

    val data = MutableLiveData<List<ProductResponseItem>>()

    val loading = MutableLiveData<Boolean>()

    val error = MutableLiveData<String>()

    fun getProducts() {
        viewModelScope.launch {
            loading.value = true
            try {
                val response = api.getAllProducts()

                if (response.isSuccessful) {
                    response.body()?.let {
                        data.value = it
                    }
                }
            } catch (e: Exception) {
                error.value = e.localizedMessage ?: "Error"
                loading.value = false
            }
        }
    }
}