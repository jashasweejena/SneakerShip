package com.example.sneakership.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.sneakership.data.local.sneaker.SneakerUiItem
import com.example.sneakership.network.Resource
import com.example.sneakership.network.sneaker.SneakerRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SneakersListViewModel @ViewModelInject constructor(
    private val sneakerRepository: SneakerRepository
): ViewModel() {
    private val _sneakersLiveData: MutableLiveData<Resource<List<SneakerUiItem>>> = MutableLiveData()
    val sneakersLiveData: LiveData<Resource<List<SneakerUiItem>>> get() = _sneakersLiveData

    fun fetchSneakers() {
        viewModelScope.launch {
            sneakerRepository.getSneakers().collect { sneakers ->
                _sneakersLiveData.value = sneakers
            }
        }
    }

    fun searchSneakers(searchQuery: String) {
        viewModelScope.launch {
            sneakerRepository.searchSneakers(searchQuery).collect {
                _sneakersLiveData.value = Resource.Success(data = it)
            }
        }
    }
}