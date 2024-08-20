package com.example.fooddeliveryapp.model

import java.util.Date
import java.util.UUID

data class Cart(
    val cartId: String,
    val date: Date,
    var cartItemsList: List<CartItem>,
    var subtotal: Double,
    var tax: Double,
    var total: Double
) {


    constructor() : this(
        cartId = UUID.randomUUID().toString(),
        date = Date(),
        cartItemsList = emptyList(),
        subtotal = 0.0,
        tax = 0.0,
        total = 0.0
    )

    companion object {
        const val TAX: Double = 0.07
        const val DELIVERY_SERVICE: Double = 10.0
    }

    fun addCartItem(cartItem: CartItem) {
        cartItemsList += cartItem
        recalculateCart()
    }

    fun removeCartItem(cartItem: CartItem){
        cartItemsList -= cartItem
        recalculateCart()
    }

    fun onQuantityUpdated(cartItem: CartItem, isAddition: Boolean) {

        var quantity = cartItem.quantity

        if(!isAddition) {
            quantity --
        } else {
            quantity ++
        }

        if(cartItemsList.contains(cartItem)) {
            cartItem.updateQuantity(quantity)
            recalculateCart()
        }
    }

    fun recalculateCart() {

        subtotal = 0.0

        cartItemsList.forEach{item->
            subtotal += item.itemTotal
        }

        tax = subtotal * TAX
        total = subtotal + tax + DELIVERY_SERVICE
    }

    fun getCartSize() : Int {
        return cartItemsList.sumOf {
            it.quantity
        }
    }

    fun clearCart() {
        cartItemsList = emptyList()
        recalculateCart()
    }

}
