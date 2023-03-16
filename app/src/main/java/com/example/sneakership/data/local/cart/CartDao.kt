package com.example.sneakership.data.local.cart

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {
    @Query("SELECT * FROM cart_items")
    fun getCartItems(): Flow<List<CartItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCartItem(cartItem: CartItem)

    @Delete
    suspend fun deleteCartItem(cartItem: CartItem)

    @Query("UPDATE cart_items SET quantity=:updatedQuantity WHERE id=:id")
    suspend fun updateQuantity(id: Long, updatedQuantity: Int)

    @Query("DELETE FROM cart_items")
    suspend fun deleteAllCartItems()

    @Query("SELECT SUM(price * quantity) AS totalPrice FROM cart_items")
    fun getSubTotal(): Flow<Double?>
}