package com.example.sneakership.feature.cart.data.local

import com.example.sneakership.feature.cart.domain.models.CartItem
import com.example.sneakership.feature.cart.data.CartDataSource
import javax.inject.Inject

class CartLocalDataSourceImpl @Inject constructor(private val cartDao: CartDao) : CartDataSource {
    override suspend fun insertItem(item: CartItem) {
        cartDao.insertCartItem(item)
    }

    override suspend fun deleteItem(item: CartItem) {
        cartDao.deleteCartItem(item)
    }

    override suspend fun updateItem(cartItem: CartItem, quantity: Int) {
        cartDao.updateQuantity(cartItem.id, quantity)
    }

    override fun getAllItems() = cartDao.getCartItems()

    override fun getTotalPrice() = cartDao.getSubTotal()

}