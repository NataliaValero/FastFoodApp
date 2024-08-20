package com.example.fooddeliveryapp.model

import java.util.UUID

data class CartItem(
    val id: String,
    val productId: String,
    val productName: String,
    val imageUrl: String,
    val productPrice: Double,
    var quantity: Int,
    var itemTotal:Double

) {
    constructor(product: Product, quantity: Int) : this(
        id = UUID.randomUUID().toString(),
        productId = product.id,
        productName = product.name,
        imageUrl = product.imageUrl,
        productPrice = product.price,
        quantity = quantity,
        itemTotal = product.price * quantity
    )

    constructor(product: Product) : this(
        product = product,
        quantity = 1
    )

    fun updateQuantity(newQuantity: Int) {
        this.quantity = newQuantity
        itemTotal = productPrice * quantity
    }
}
