package com.example.androidbootcamp10yul.api

import com.example.androidbootcamp10yul.model.ProductResponseItem
import retrofit2.Response
import retrofit2.http.GET

interface StoreApi {

    @GET("products")
    suspend fun getAllProducts(): Response<List<ProductResponseItem>>
}