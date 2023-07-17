package com.example.androidbootcamp10yul.repository

import com.example.androidbootcamp10yul.api.StoreApi
import com.example.androidbootcamp10yul.base.NetworkResponseState
import com.example.androidbootcamp10yul.model.ProductResponseItem
import retrofit2.Response
import javax.inject.Inject

class StoreRepository @Inject constructor(
    private val api: StoreApi
) {

    suspend fun getHomeData(): NetworkResponseState<Response<List<ProductResponseItem>>> =
        safeApiRequest {
            api.getAllProducts()
        }


    private suspend fun <T : Any> safeApiRequest(apiRequest: suspend () -> T): NetworkResponseState<T> {
        return try {
            NetworkResponseState.Success(apiRequest.invoke())
        } catch (exception: Exception) {
            NetworkResponseState.Error(exception)
        }
    }
}