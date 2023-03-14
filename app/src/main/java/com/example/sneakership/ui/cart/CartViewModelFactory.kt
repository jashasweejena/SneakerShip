package com.example.sneakership.ui.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sneakership.data.local.CartDao

class CartViewModelFactory(private val cartDao: CartDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CartViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CartViewModel(cartDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}