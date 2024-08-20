package com.example.fooddeliveryapp.ui.Activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.data.repository.MenuRepositoryImpl
import com.example.fooddeliveryapp.data.repository.SharedPreferencesRepositoryImpl
import com.example.fooddeliveryapp.data.source.FirebaseFactory
import com.example.fooddeliveryapp.data.source.MenuDataSource
import com.example.fooddeliveryapp.data.source.SharedPreferencesDataSource
import com.example.fooddeliveryapp.data.viewModel.MenuVMFactory
import com.example.fooddeliveryapp.data.viewModel.MenuViewModel
import com.example.fooddeliveryapp.databinding.FragmentMenuBinding
import com.example.fooddeliveryapp.model.Category
import com.example.fooddeliveryapp.model.Product
import com.example.fooddeliveryapp.ui.Adapter.CategoryAdapter
import com.example.fooddeliveryapp.ui.Adapter.ProductAdapter
import com.example.fooddeliveryapp.utils.showCustomToast


class MenuFragment : Fragment(R.layout.fragment_menu) {

    private lateinit var binding: FragmentMenuBinding

    private lateinit var categoriesAdapter: CategoryAdapter

    private lateinit var recommendedAdapter: ProductAdapter

    private lateinit var productsAdapter: ProductAdapter

    private val viewModel: MenuViewModel by activityViewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMenuBinding.bind(view)

        loadMenu()
        suscribeObservers()
        initializeAdapters()
        setUpSearchView()
        setUpViews(view)
    }

    private fun loadMenu() {
        viewModel.loadMenu()
    }

    private fun suscribeObservers() = with(binding) {

        viewModel.categories.observe(viewLifecycleOwner) {
            categoriesAdapter.setList(it)
        }
        viewModel.recommended.observe(viewLifecycleOwner) {
            recommendedAdapter.setList(it)
        }

        viewModel.products.observe(viewLifecycleOwner) {
            productsAdapter.setList(it)
        }

    }


    private fun initializeAdapters() {

        categoriesAdapter = CategoryAdapter(emptyList(), object : CategoryAdapter.onItemClick {
            override fun onClick(category: Category) {
                viewModel.getProductsByCategoryId(category.id)
            }
        })

        recommendedAdapter = ProductAdapter(emptyList(), object : ProductAdapter.onItemClick {
            override fun onClick(product: Product) {
                viewModel.onProductSelected(product)
                view?.findNavController()?.navigate(R.id.action_menuFragment_to_productDetailFragment)
            }

            override fun onAddClick(product: Product) {
                viewModel.onProductSelected(product)
                viewModel.addProductToCart()
                //Toast.makeText(context, "Product added to cart succesfully", Toast.LENGTH_SHORT).show()
                Toast(requireContext()).showCustomToast("Product added to cart succesfully", requireActivity())
            }

        })

        productsAdapter = ProductAdapter(emptyList(), object : ProductAdapter.onItemClick {
            override fun onClick(product: Product) {
                viewModel.onProductSelected(product)
                view?.findNavController()?.navigate(R.id.action_menuFragment_to_productDetailFragment)
            }

            override fun onAddClick(product: Product) {

                viewModel.onProductSelected(product)
                viewModel.addProductToCart()
               // Toast(requireContext()).showCustomToast("Product added to cart succesfully", requireActivity())

            }

        })



        binding.apply {
            categoriesRv.apply {
                adapter = categoriesAdapter
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            }
            recommendedRv.apply {
                adapter = recommendedAdapter
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            }
            productsRv.apply {
                adapter = productsAdapter
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            }
        }
    }


    private fun setUpSearchView() {

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {

                filterProducts(newText)
                return true
            }
        })
    }

    private fun filterProducts(text: String) {
        val filteredList = viewModel.products.value?.filter { product ->
            // Dividir el texto de búsqueda en palabras separadas
            val searchWords = text.split(" ")

            // Verificar si cada palabra de búsqueda está contenida en el nombre del producto
            searchWords.all { searchWord ->
                product.name.startsWith(searchWord, ignoreCase = true)
            }
        }

        if (!filteredList.isNullOrEmpty()) {
            productsAdapter.setList(filteredList)
        }
    }

    private fun setUpViews(view: View) {
        binding.profilePicture.setOnClickListener {
            view.findNavController().navigate(R.id.action_menuFragment_to_profileFragment)
        }
    }

}