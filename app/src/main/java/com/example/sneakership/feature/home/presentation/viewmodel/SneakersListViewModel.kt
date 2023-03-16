package com.example.sneakership.feature.home.presentation.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.sneakership.feature.cart.domain.models.CartItem
import com.example.sneakership.data.local.sneaker.SneakerUiItem
import com.example.sneakership.feature.home.enums.SortOrder
import com.example.sneakership.utils.network.Resource
import com.example.sneakership.feature.home.domain.repository.SneakerRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
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

    fun addToCart(item: CartItem) {

    }

}

