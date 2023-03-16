package com.example.sneakership.feature.cart.data.repository

import com.example.sneakership.feature.cart.domain.models.CartItem
import com.example.sneakership.feature.cart.data.CartDataSource
import com.example.sneakership.feature.cart.domain.repository.CartRepository
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(private val localDataSource: CartDataSource):
    CartRepository {
    override fun getCartItems() = localDataSource.getAllItems()
    override fun totalPrice() = localDataSource.getTotalPrice()

    override suspend fun insertItem(item: CartItem) {
        localDataSource.insertItem(item)
    }

    override suspend fun deleteCartItem(item: CartItem) {
        localDataSource.deleteItem(item)
    }

    override suspend fun updateQuantity(cartItem: CartItem, quantity: Int) {
        localDataSource.updateItem(cartItem, quantity)
    }

}