package com.example.sneakership.data.local.cart

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_items")
data class CartItem(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val sneakerId: Long,
    val name: String,
    val price: Double,
    val quantity: Int = 1
)