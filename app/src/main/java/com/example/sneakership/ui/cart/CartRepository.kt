package com.example.sneakership.ui.cart

import com.example.sneakership.data.local.cart.CartDao
import com.example.sneakership.data.local.cart.CartItem
import javax.inject.Inject

class CartRepository @Inject constructor(private val cartDao: CartDao) {
    fun getCartItems() = cartDao.getCartItems()
    fun totalPrice() = cartDao.getSubTotal()

    suspend fun insertItem(item: CartItem) {
        cartDao.insertCartItem(item)
    }

    suspend fun deleteCartItem(item: CartItem) {
        cartDao.deleteCartItem(item)
    }

    suspend fun clearCart() {
        cartDao.deleteAllCartItems()
    }

    suspend fun updateQuantity(cartItem: CartItem, quantity: Int) {
        cartDao.updateQuantity(cartItem.id, quantity)
    }

}