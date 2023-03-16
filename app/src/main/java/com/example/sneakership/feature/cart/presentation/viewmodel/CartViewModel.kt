package com.example.sneakership.feature.cart.presentation.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sneakership.feature.cart.domain.models.CartItem
import com.example.sneakership.feature.cart.domain.repository.CartRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

class CartViewModel @ViewModelInject constructor(private val repository: CartRepository) : ViewModel() {

    private val _cartItems: MutableLiveData<List<CartItem>> = MutableLiveData()
    val cartItems: LiveData<List<CartItem>> get() = _cartItems

    private val _subTotal: MutableLiveData<Int> = MutableLiveData()
    val subTotal: LiveData<Int> get() = _subTotal

    private val _total: MutableLiveData<Int> = MutableLiveData()
    val total: LiveData<Int> get() = _total

    val taxesAndCharges: Double = 40.0

    init {
        getAllCartItems()
        getTotalPrice()
    }
    fun insertCartItem(cartItem: CartItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertItem(cartItem)
        }
    }

    fun deleteCartItem(cartItem: CartItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteCartItem(cartItem)
        }
    }

    fun incrementCartItem(cartItem: CartItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateQuantity(cartItem, cartItem.quantity + 1)
        }
    }

    fun decrementCartItem(cartItem: CartItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateQuantity(cartItem, cartItem.quantity - 1)
        }
    }

    private fun getAllCartItems() {
        viewModelScope.launch {
            repository.getCartItems().collect { cartItems ->
                _cartItems.postValue(cartItems)
            }
        }
    }

    private fun getTotalPrice() {
        viewModelScope.launch {
            repository.totalPrice().collect {totalPrice ->
                totalPrice?.let {
                    _subTotal.postValue(it.roundToInt())
                    _total.postValue((it + taxesAndCharges).roundToInt())
                } ?: run{
                    _subTotal.postValue(0)
                    _total.postValue(0)
                }
            }
        }
    }


}