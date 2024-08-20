package com.example.fooddeliveryapp.ui.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fooddeliveryapp.data.repository.MenuRepositoryImpl
import com.example.fooddeliveryapp.data.source.FirebaseFactory
import com.example.fooddeliveryapp.data.source.MenuDataSource
import com.example.fooddeliveryapp.data.viewModel.MenuVMFactory
import com.example.fooddeliveryapp.data.viewModel.MenuViewModel
import com.example.fooddeliveryapp.databinding.CategoryItemViewholderBinding
import com.example.fooddeliveryapp.model.CartItem
import com.example.fooddeliveryapp.model.Formatter



class CartItemAdapter (var cartItemsList: List<CartItem>, var listener: cartItemAdapterListener? = null) :RecyclerView.Adapter<CartItemAdapter.CartItemViewHolder>() {



    inner class CartItemViewHolder(private val binding: CategoryItemViewholderBinding) :RecyclerView.ViewHolder(binding.root) {

        fun bind(cartItem: CartItem) = with(binding) {

            cartItem.let {

                // Image
                Glide.with(root.context)
                    .load(it.imageUrl)
                    .into(productImage)

                //product name
                productName.setText(it.productName)

                //quantity
                quantityBar.quantity.setText(it.quantity.toString())

                //price
                productPrice.setText(Formatter.getFormattedCurrency(it.productPrice))

                //total
                productTotal.setText(Formatter.getFormattedCurrency(it.itemTotal))


                // Listeners
                // on remove
                removeIc.setOnClickListener {
                    listener?.onRemoveClick(cartItem)
                    notifyDataSetChanged()
                }

                // on quantity changed minus click
                quantityBar.minus.setOnClickListener {

                    if(cartItem.quantity > 1) {
                        listener?.onQuantityChanged(cartItem, false)
                    }
                    notifyDataSetChanged()
                }
                // on quantity changed plus click
                quantityBar.plus.setOnClickListener {

                    listener?.onQuantityChanged(cartItem, true)
                    notifyDataSetChanged()
                }


            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemViewHolder {
        return CartItemViewHolder(CategoryItemViewholderBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return cartItemsList.size
    }

    override fun onBindViewHolder(holder: CartItemViewHolder, position: Int) {

        holder.bind(cartItemsList.get(position))
    }

    fun setList(newList : List<CartItem>){
        cartItemsList = newList
        notifyDataSetChanged()
    }


    interface cartItemAdapterListener {

        fun onRemoveClick(cartItem: CartItem)

        fun onQuantityChanged(cartItem: CartItem, isAddition: Boolean)
    }
}