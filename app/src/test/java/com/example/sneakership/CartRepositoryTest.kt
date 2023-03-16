package com.example.sneakership

import com.example.sneakership.feature.cart.data.CartDataSource
import com.example.sneakership.feature.cart.data.repository.CartRepositoryImpl
import com.example.sneakership.feature.cart.domain.models.CartItem
import com.example.sneakership.feature.cart.domain.repository.CartRepository
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.singleOrNull
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Test

class CartRepositoryTest {
    private val cartLocalDataSource: CartDataSource = mockk()
    private val cartRepository: CartRepository = CartRepositoryImpl(cartLocalDataSource)

    @Test
    fun `insert item`() = runBlocking {
        // Arrange
        val cartItem = CartItem(1, "fcf32b", "Nike Air Jordans", 100.0, 1, "")
        coEvery { cartLocalDataSource.insertItem(cartItem) } just Runs

        // Act
        cartRepository.insertItem(cartItem)

        // Assert
        coVerify { cartLocalDataSource.insertItem(cartItem) }
    }

    @Test
    fun `delete cart item`() = runBlocking {
        // Arrange
        val cartItem = CartItem(1, "fcf32b", "Nike Air Jordans", 100.0, 1, "")
        coEvery { cartLocalDataSource.deleteItem(cartItem) } just Runs

        // Act
        cartRepository.deleteCartItem(cartItem)

        // Assert
        coVerify { cartLocalDataSource.deleteItem(cartItem) }
    }

    @Test
    fun `update cart item quantity`() = runBlocking {
        // Arrange
        val cartItem = CartItem(1, "fcf32b", "Nike Air Jordans", 100.0, 1, "")
        val newQuantity = 2
        coEvery { cartLocalDataSource.updateItem(cartItem, newQuantity) } just Runs

        // Act
        cartRepository.updateQuantity(cartItem, newQuantity)

        // Assert
        coVerify { cartLocalDataSource.updateItem(cartItem, newQuantity) }
    }

    @Test
    fun `get cart items`() = runBlocking {
        // Arrange
        val cartItems = listOf(
            CartItem(1, "fcf32b", "Nike Air Jordans", 100.0, 1, ""),
            CartItem(1, "fcf32b", "Nike Air Jordans", 100.0, 1, "")
        )
        val flow = flowOf(cartItems)
        every { cartLocalDataSource.getAllItems() } returns flow

        // Act
        val result = cartRepository.getCartItems().toList()

        // Assert
        assertEquals(listOf(cartItems), result)
    }

    @Test
    fun `test total price`() {
        // given
        val cartItems = listOf(
            CartItem(1, "fcf32b", "Nike Air Jordans", 100.0, 1, ""),
            CartItem(1, "fcf32b", "Nike Air Jordans", 100.0, 1, ""),
            CartItem(1, "fcf32b", "Nike Air Jordans", 100.0, 1, ""),
        )
        val totalPrice = cartItems.sumOf { it.price * it.quantity }
        every { cartRepository.totalPrice() } returns flowOf(totalPrice)

        // when
        val result = runBlocking { cartRepository.totalPrice().singleOrNull() }

        // then
        assertEquals(totalPrice, result)
    }
}