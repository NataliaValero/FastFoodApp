package com.example.fooddeliveryapp.data.repository

import com.example.fooddeliveryapp.model.Category
import com.example.fooddeliveryapp.model.Product


interface MenuRepository {


    // Categories
    fun addCategories(categories : List<Category>)
    suspend fun getCategories() : List<Category>?

    // Products
    fun addProducts(products: List<Product>)
    suspend fun getRecommendedProducts() : List<Product>?
    suspend fun getProductsByCategoryId(categoryId: Long) : List<Product>?
}