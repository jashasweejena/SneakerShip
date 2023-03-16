package com.example.sneakership.feature.cart.domain.repository

import com.example.sneakership.feature.cart.domain.models.CartItem
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    suspend fun insertItem(item: CartItem)
    suspend fun deleteCartItem(item: CartItem)
    suspend fun updateQuantity(cartItem: CartItem, quantity: Int)
    fun getCartItems(): Flow<List<CartItem>>
    fun totalPrice(): Flow<Double?>
}