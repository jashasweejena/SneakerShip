package com.example.sneakership.network

import com.example.sneakership.data.local.sneaker.SneakerUiItem
import com.example.sneakership.network.sneaker.SneakersListDtoItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import java.lang.Exception

class ResponseHandler {
    suspend fun <T, V> safeApiCall(apiCall: suspend () -> Response<T>, mapper: (t: T) -> V) =
        flow {
            try {
                emit(Resource.Loading(isLoading = true))
                val response = apiCall()
                response.body()?.let {
                    if (response.isSuccessful) {
                        emit(Resource.Success(data = mapper(it)))
                    } else {
                        var errorMessage = "Something went wrong"
                        val errorBody = response.errorBody()?.string()
                        if (!errorBody.isNullOrEmpty()) {
                            errorMessage = errorBody
                        }
                        emit(Resource.Error(message = errorMessage, throwable = null))
                    }
                }
            } catch (e: Exception) {
                emit(Resource.Error(message = e.message ?: e.toString(), throwable = e.cause))
            }
            emit(Resource.Loading(isLoading = false))
        }.flowOn(Dispatchers.IO)
}