package com.example.sneakership.utils.network

sealed class Resource<V>(
    val data: V? = null,
    val message: String? = null,
    val error: Throwable? = null,
    val isLoading: Boolean = false
) {
    class Success<V>(data: V) : Resource<V>(data)
    class Loading<V>(data: V? = null, isLoading: Boolean) : Resource<V>(data, isLoading = isLoading)
    class Error<V>(message: String, throwable: Throwable?) : Resource<V>(null, message, throwable)
}