package com.example.sneakership.ui.cart

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.sneakership.data.local.cart.CartDao
import com.example.sneakership.data.local.cart.CartItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CartViewModel @ViewModelInject constructor(private val cartDao: CartDao) : ViewModel() {

    val cartItems: LiveData<List<CartItem>> = cartDao.getCartItems().asLiveData()

    fun insertCartItem(cartItem: CartItem) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                cartDao.insertCartItem(cartItem)
            }
        }
    }

    fun deleteCartItem(cartItem: CartItem) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                cartDao.deleteCartItem(cartItem)
            }
        }
    }

    fun clearCart() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                cartDao.deleteAllCartItems()
            }
        }
    }
}