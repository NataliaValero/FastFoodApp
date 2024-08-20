package com.example.fooddeliveryapp.data.source

import android.util.Log
import com.example.fooddeliveryapp.model.Category
import com.example.fooddeliveryapp.model.Product
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.tasks.await

class MenuDataSource (private val firebase: FirebaseFirestore) {



    fun addCategories(categories: List<Category>) {

        categories.forEach {category->
            firebase.collection("/menu/foodapp/categories")
                .document(category.id.toString())
                .set(category)
                .addOnSuccessListener {
                    Log.v("categories", "Category added")
                }
                .addOnFailureListener { e->
                    Log.w("categories", "Error adding category", e)
                }
        }
    }

    fun addProducts(products: List<Product>) {

        products.forEach { product->
            firebase.collection("/menu/foodapp/products")
                .document(product.id)
                .set(product)
                .addOnSuccessListener {
                    Log.v("products", "Product added")
                }
                .addOnFailureListener {e->
                    Log.v("products", "Error adding product", e)
                }
        }
    }


    suspend fun getCategories() : List<Category>? {

        return try {
            val categories = mutableListOf<Category>()

            val documents = firebase.collection("/menu/foodapp/categories")
                .orderBy("ordinal")
                .get()
                .await()
                .documents


            documents.forEach {
                val category = it.toObject(Category::class.java)
                if (category != null) {
                    categories.add(category)
                }
            }

            categories

        } catch (e:Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    suspend fun getRecommendedProducts() : List<Product>? {

        return try {
            val products = mutableListOf<Product>()

            val documents = firebase.collection("/menu/foodapp/products")
                .whereGreaterThan("rating", 4.5)
                .orderBy("rating")
                .get()
                .await()
                .documents

            documents.forEach {
                val product = it.toObject(Product::class.java)
                if (product != null) {
                    products.add(product)
                }
            }

            products
        } catch (e:Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    suspend fun getProductsByCategoryId(categoryId: Long) : List<Product> {
        return try {
            val products = mutableListOf<Product>()
            val documents = firebase.collection("/menu/foodapp/products")
                .whereEqualTo("categoryId", categoryId)
                .get()
                .await()
                .documents


            documents.forEach {
                val product = it.toObject(Product::class.java)
                if (product != null) {
                    products.add(product)
                }
            }

            products

        } catch (e:Exception) {
            e.printStackTrace()
            emptyList()
        }
    }


}