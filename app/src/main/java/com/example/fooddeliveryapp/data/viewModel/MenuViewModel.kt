package com.example.fooddeliveryapp.data.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.fooddeliveryapp.data.repository.MenuRepository
import com.example.fooddeliveryapp.data.repository.SharedPreferencesRepository
import com.example.fooddeliveryapp.helper.MenuFactory
import com.example.fooddeliveryapp.model.Cart
import com.example.fooddeliveryapp.model.CartItem
import com.example.fooddeliveryapp.model.Category
import com.example.fooddeliveryapp.model.Product
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class MenuViewModel (private val menuRepository: MenuRepository, private val  sharedPreferencesRepository: SharedPreferencesRepository) :ViewModel() {

    var categories : MutableLiveData<List<Category>> = MutableLiveData()
    var recommended: MutableLiveData<List<Product>> = MutableLiveData()
    var products: MutableLiveData<List<Product>> = MutableLiveData()
    var productSelected: Product = Product()

    var cart: Cart = Cart()
    var currentCartSize: MutableLiveData<Int> = MutableLiveData()


   fun addCategories() {
        menuRepository.addCategories(MenuFactory.getCategories())
    }

    fun addProducts() {
        menuRepository.addProducts(MenuFactory.getProducts())
    }


    fun loadMenu() {
        getCategories()
        getRecommendedProducts()
    }

    fun getCategories() = viewModelScope.launch {
        menuRepository.getCategories()?.let {
            categories.value = it

            // Trae los productos del primer category (category seleccionado por defecto)
            getProductsByCategoryId(it.first().id)

        }
    }

    fun getRecommendedProducts() = viewModelScope.launch {
        menuRepository.getRecommendedProducts()?.let {
            recommended.value = it
        }
    }

    fun getProductsByCategoryId(categoryId: Long) = viewModelScope.launch {
        menuRepository.getProductsByCategoryId(categoryId)?.let {
            products.value = it
        }
    }

    fun onProductSelected(product: Product) {
        productSelected = product
    }


    // Cart

    fun getCartItems() : List<CartItem> {
        return cart.cartItemsList
    }

    fun addProductToCart(quantity: Int) {
        val cartItem = CartItem(productSelected, quantity)
        cart.addCartItem(cartItem)
        updateCartSize()

    }

    // override method when quantity is not needed
    fun addProductToCart() {
        val cartItem = CartItem(productSelected)
        cart.addCartItem(cartItem)
        updateCartSize()

    }

    fun removeCartItem(cartItem: CartItem) {
        cart.removeCartItem(cartItem)
        updateCartSize()
    }

    fun onQuantityUpdated(cartItem: CartItem, isAddition: Boolean) {
        cart.onQuantityUpdated(cartItem, isAddition)
        updateCartSize()
    }

    fun isCartEmpty() : Boolean {
        return cart.cartItemsList.isEmpty()
    }

    fun clearCart() {
        cart.clearCart()
        updateCartSize()
    }


    fun updateCartSize() {
        currentCartSize.value = cart.getCartSize()
    }

    // Update shared preferences to indicate that the application is not running for the first time anymore
    fun isFirstTimeAppLaunch() : Boolean {
        return sharedPreferencesRepository.isFirstTimeAppLaunch()
    }

    fun appAlreadyLaunched() {
        sharedPreferencesRepository.appAlreadyLaunched()
    }

}

class MenuVMFactory(private val menuRepository: MenuRepository, private val sharedPreferencesRepository: SharedPreferencesRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MenuViewModel::class.java)) {
            return MenuViewModel(menuRepository, sharedPreferencesRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}