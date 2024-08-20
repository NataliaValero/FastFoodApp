package com.example.fooddeliveryapp.data.repository


import com.example.fooddeliveryapp.data.source.MenuDataSource
import com.example.fooddeliveryapp.model.Category
import com.example.fooddeliveryapp.model.Product

class MenuRepositoryImpl(private val menuDataSource: MenuDataSource) : MenuRepository {
    override fun addCategories(categories: List<Category>) {
        return menuDataSource.addCategories(categories)
    }

    override suspend fun getCategories(): List<Category>? {
        return menuDataSource.getCategories()
    }

    override fun addProducts(products: List<Product>) {
        menuDataSource.addProducts(products)
    }

    override suspend fun getRecommendedProducts(): List<Product>? {
        return menuDataSource.getRecommendedProducts()
    }

    override suspend fun getProductsByCategoryId(categoryId: Long): List<Product>? {
        return menuDataSource.getProductsByCategoryId(categoryId)
    }
}