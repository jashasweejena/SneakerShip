package com.example.sneakership.feature.cart.data

import com.example.sneakership.feature.cart.domain.models.CartItem
import kotlinx.coroutines.flow.Flow

interface CartDataSource {
    suspend fun insertItem(item: CartItem)
    suspend fun deleteItem(item: CartItem)
    suspend fun updateItem(cartItem: CartItem, quantity: Int)
    fun getAllItems(): Flow<List<CartItem>>
    fun getTotalPrice(): Flow<Double?>
}