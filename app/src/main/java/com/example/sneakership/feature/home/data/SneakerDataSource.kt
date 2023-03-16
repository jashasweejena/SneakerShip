package com.example.sneakership.feature.home.data

import com.example.sneakership.data.local.sneaker.SneakerUiItem
import com.example.sneakership.feature.home.enums.SortOrder
import com.example.sneakership.utils.network.Resource
import kotlinx.coroutines.flow.Flow

interface SneakerDataSource {
    suspend fun getSneakers(): Flow<Resource<List<SneakerUiItem>>>
    suspend fun saveSneakers(list: List<SneakerUiItem>)
    fun searchSneakers(searchQuery: String, sortOrder: SortOrder): Flow<Resource<List<SneakerUiItem>>>
}