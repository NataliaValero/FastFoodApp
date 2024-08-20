package com.example.fooddeliveryapp.model

import java.util.UUID

data class Product(
    val id: String,
    val name: String,
    val price: Double,
    val rating: Double,
    val waitingTime: Long,
    val calories:Int,
    val description:String,
    val imageUrl:String,
    val categoryId: Long
) {
    constructor() :this("","",0.0,0.0,0,0,"","",0)
}
