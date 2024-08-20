package com.example.fooddeliveryapp.ui.Adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.databinding.ProductViewhodelBinding
import com.example.fooddeliveryapp.model.Category
import com.example.fooddeliveryapp.model.Formatter
import com.example.fooddeliveryapp.model.Product



class ProductAdapter(var products: List<Product>, var listener: onItemClick) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>(){



    inner class ProductViewHolder(private val binding: ProductViewhodelBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) = with(binding) {



            // Cargar imagen
            Glide.with(root.context)
                .load(product.imageUrl)
                .into(productPic)

            productNametv.setText(product.name)
            priceProductTv.setText(Formatter.getFormattedCurrency(product.price))


            productItem.setOnClickListener{
                listener.onClick(product)
            }

            addIc.setOnClickListener {


                listener.onAddClick(product)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(ProductViewhodelBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(products.get(position))
    }

    fun setList(newList: List<Product>) {
        products = newList
        notifyDataSetChanged()
    }


    interface onItemClick {
        fun onClick(product: Product)
        fun onAddClick(product: Product)
    }

}