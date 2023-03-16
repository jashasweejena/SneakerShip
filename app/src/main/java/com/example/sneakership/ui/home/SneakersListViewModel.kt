package com.example.sneakership.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.room.util.query
import com.example.sneakership.data.local.sneaker.SneakerUiItem
import com.example.sneakership.network.Resource
import com.example.sneakership.network.sneaker.SneakerRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class SneakersListViewModel @ViewModelInject constructor(
    private val sneakerRepository: SneakerRepository
) : ViewModel() {
    private val _sneakersLiveData: MutableLiveData<Resource<List<SneakerUiItem>>> =
        MutableLiveData()
    val sneakersLiveData: LiveData<Resource<List<SneakerUiItem>>> get() = _sneakersLiveData

    val searchQuery = MutableStateFlow("")
    val sortOrder = MutableStateFlow(SortOrder.BY_NAME)

    private val _searchLiveData = combine(
        searchQuery, sortOrder
    ) { query, sortOrder ->
        Pair(query, sortOrder)
    }.flatMapLatest { (query, sortOrder) ->
        sneakerRepository.searchSneakers(query, sortOrder)
    }.asLiveData()

    private val _networkLiveData = MediatorLiveData<Resource<List<SneakerUiItem>>>()

    val uiLiveData = MediatorLiveData<Resource<List<SneakerUiItem>>>().apply {
        addSource(_searchLiveData) {
            value = it
        }
        addSource(_networkLiveData) {
            value = it
        }
    }


    fun fetchSneakers() {
        viewModelScope.launch {
            sneakerRepository.getSneakers().collect { sneakers ->
                _networkLiveData.value = sneakers
            }
        }
    }

}

