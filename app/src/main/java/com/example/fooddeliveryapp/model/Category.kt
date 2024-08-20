package com.example.fooddeliveryapp.model

data class Category(
    val id:Long,
    val name:String,
    val imageResourceId:Int,
    val ordinal:Int
) {
    // Constructor vacîo
    constructor() : this(0, "", 0, 0)
}

