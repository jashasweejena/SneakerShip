package com.example.sneakership.network.sneaker

import com.example.sneakership.data.local.sneaker.SneakerDao
import com.example.sneakership.data.local.sneaker.SneakerUiItem
import com.example.sneakership.network.IOnlineChecker
import com.example.sneakership.network.Resource
import com.example.sneakership.network.ResponseHandler
import com.example.sneakership.ui.home.SortOrder
import com.example.sneakership.ui.home.data.toUiList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SneakerRepository @Inject constructor(
    private val apiService: SneakerApiService,
    private val sneakerDao: SneakerDao,
    private val onlineChecker: IOnlineChecker,
    private val responseHandler: ResponseHandler
) {

    suspend fun getSneakers() = flow {
        val sneakers = sneakerDao.getAll().firstOrNull()
        if (sneakers == null || onlineChecker.isOnline()) {
            responseHandler.safeApiCall(
                apiCall = { apiService.getSneakersList() },
                mapper = { it.toUiList() }
            ).collect { resource ->
                if (resource is Resource.Success) {
                    resource.data?.let {
                        sneakerDao.insertAll(it)
                    }
                }
                emit(resource)
            }
        } else {
            emit(Resource.Success(data = sneakers))
        }
    }.flowOn(Dispatchers.IO)

    fun searchSneakers(searchQuery: String, sortOrder: SortOrder): Flow<Resource<List<SneakerUiItem>>> = flow {
        emit(Resource.Loading(isLoading = true))
        sneakerDao.search(searchQuery, sortOrder).map { Resource.Success(data = it) }.collect {
            emit(it)
            emit(Resource.Loading(isLoading = false))
        }
    }

}

