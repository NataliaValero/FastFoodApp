package com.example.fooddeliveryapp.ui.Activity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.data.repository.MenuRepositoryImpl
import com.example.fooddeliveryapp.data.repository.SharedPreferencesRepositoryImpl
import com.example.fooddeliveryapp.data.source.FirebaseFactory
import com.example.fooddeliveryapp.data.source.MenuDataSource
import com.example.fooddeliveryapp.data.source.SharedPreferencesDataSource
import com.example.fooddeliveryapp.data.viewModel.MenuVMFactory
import com.example.fooddeliveryapp.data.viewModel.MenuViewModel
import com.example.fooddeliveryapp.databinding.FragmentProductDetailBinding
import com.example.fooddeliveryapp.model.Formatter
import com.example.fooddeliveryapp.utils.showCustomToast


class ProductDetailFragment : Fragment(R.layout.fragment_product_detail) {

    private lateinit var binding: FragmentProductDetailBinding

    private val viewModel: MenuViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProductDetailBinding.bind(view)



        loadProduct()
        setUpViews()

    }


    private fun loadProduct() = with(binding) {

        var productSelected = viewModel.productSelected

        productSelected.let {

            Glide.with(root.context)
                .load(it.imageUrl)
                .into(imageProduct)

            productName.text = it.name
            productPrice.text = Formatter.getFormattedCurrency(it.price)
            rating.text = it.rating.toString()
            time.text = Formatter.getFormattedTime(it.waitingTime)
            calories.text = Formatter.getFormattedCalories(it.calories)
            ingredients.text = it.description
        }
    }

    private fun setUpViews() = with(binding) {


        // Update quantity and totals
        var productQuantityTv = binding.quantityBar.quantity.text
        var quantity = productQuantityTv.toString().toInt()

        quantityBar.minus.setOnClickListener {
            if(quantity > 1) {
                quantity --
                refreshTotals(quantity)
            }
        }
        quantityBar.plus.setOnClickListener {
            quantity ++
            refreshTotals(quantity)
        }

        // Agrega product a carro con la cantidad actual
        addToCartTv.setOnClickListener {
            viewModel.addProductToCart(quantity)
           // Toast(requireContext()).showCustomToast("Product added to cart succesfully", requireActivity())
            Log.v("Selected", viewModel.cart.toString())

        }

    }

    private fun refreshTotals(quantity :Int) = with(binding) {

        val product = viewModel.productSelected
        val total = product.price * quantity

        quantityBar.quantity.text = quantity.toString()
        totalPrice.text = Formatter.getFormattedCurrency(total)
    }



}
