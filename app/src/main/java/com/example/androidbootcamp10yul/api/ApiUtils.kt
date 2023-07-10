package com.example.androidbootcamp10yul.api

class ApiUtils {
    companion object {
        fun getApi(): StoreApi {
            return RetrofitClient.getRetrofitClient().create(StoreApi::class.java)
        }
    }
}