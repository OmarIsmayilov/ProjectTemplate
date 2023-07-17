package com.example.androidbootcamp10yul.base

sealed class NetworkResponseState<out T : Any> {
    data class Success<out T : Any>(val result: T) : NetworkResponseState<T>()
    data class Error(val exception: Exception) : NetworkResponseState<Nothing>()
}