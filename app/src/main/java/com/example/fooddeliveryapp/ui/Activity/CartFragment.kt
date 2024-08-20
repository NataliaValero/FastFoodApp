package com.example.fooddeliveryapp.ui.Activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.data.repository.MenuRepositoryImpl
import com.example.fooddeliveryapp.data.repository.SharedPreferencesRepositoryImpl
import com.example.fooddeliveryapp.data.source.FirebaseFactory
import com.example.fooddeliveryapp.data.source.MenuDataSource
import com.example.fooddeliveryapp.data.source.SharedPreferencesDataSource
import com.example.fooddeliveryapp.data.viewModel.MenuVMFactory
import com.example.fooddeliveryapp.data.viewModel.MenuViewModel
import com.example.fooddeliveryapp.databinding.FragmentCartBinding
import com.example.fooddeliveryapp.model.Cart
import com.example.fooddeliveryapp.model.CartItem
import com.example.fooddeliveryapp.model.Formatter
import com.example.fooddeliveryapp.ui.Adapter.CartItemAdapter
import com.example.fooddeliveryapp.ui.components.ItemDeletionDialog
import com.example.fooddeliveryapp.ui.components.ItemDeletionDialog.onSubmitClickListener as onSubmitClickListener


class CartFragment : Fragment(R.layout.fragment_cart) {

    private lateinit var binding: FragmentCartBinding

    private val viewModel: MenuViewModel by activityViewModels()

    // Adapter
    private lateinit var cartItemsAdapter: CartItemAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCartBinding.bind(view)


        setUpViews()
        refreshTotals()
        initializeAdapter()
    }

    private fun initializeAdapter() = with(binding) {


        cartItemsAdapter = CartItemAdapter(viewModel.getCartItems())
        cartItemsRv.adapter = cartItemsAdapter
        cartItemsRv.layoutManager = LinearLayoutManager(context)

        cartItemsAdapter.listener= object : CartItemAdapter.cartItemAdapterListener {
            override fun onRemoveClick(cartItem: CartItem) {

                ItemDeletionDialog<CartItem>(cartItem,
                    "Are you sure you want to delete this product?"
                    ,"Delete",
                    object :onSubmitClickListener<CartItem> {
                        override fun onItemRemovePermanently(item: CartItem) {
                            // remove cart item from the cart
                            viewModel.removeCartItem(item)
                            cartItemsAdapter.setList(viewModel.getCartItems())
                            refreshTotals()
                            setUpViews()
                        }

                    }).show(parentFragmentManager, "dialog")

            }

            override fun onQuantityChanged(cartItem: CartItem, isAddition: Boolean) {
                viewModel.onQuantityUpdated(cartItem, isAddition)
                refreshTotals()
            }


        }
    }


    private fun refreshTotals() = with(binding) {
        val cart = viewModel.cart

        cart.let {
            itemTotalValue.setText(Formatter.getFormattedCurrency(it.subtotal))
            taxValue.setText(Formatter.getFormattedCurrency(it.tax))
            deliveryValue.setText(Formatter.getFormattedCurrency(Cart.DELIVERY_SERVICE))
            totalValue.setText(Formatter.getFormattedCurrency(it.total))
        }
    }

    private fun setUpViews() {

        binding.clearCartTv.setOnClickListener {

            ItemDeletionDialog<Cart>(viewModel.cart,
                "Are you sure you want to clear out this cart?",
                "Clear All",
                object :onSubmitClickListener<Cart>{
                    override fun onItemRemovePermanently(item: Cart) {
                        viewModel.clearCart()
                        setUpViews()
                    }
                }).show(parentFragmentManager, "dialog")

            setUpViews()
        }

        if(viewModel.isCartEmpty()) {
            binding.cartGroup.visibility = View.GONE
            binding.emptyCart.visibility = View.VISIBLE
        }
    }




}