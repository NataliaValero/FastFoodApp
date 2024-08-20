package com.example.fooddeliveryapp.ui.Adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.databinding.CategoryViewholderBinding
import com.example.fooddeliveryapp.helper.CategoryType
import com.example.fooddeliveryapp.model.Category

class CategoryAdapter (var categories: List<Category>, var listener: onItemClick) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>(){

    private var selectedItem = 0

    inner class CategoryViewHolder(private val binding: CategoryViewholderBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(category: Category) = with(binding) {

            categoryNameTv.setText(category.name)
            val image = CategoryType.fromKey(category.id).imageId
            imageCategory.setImageResource(image)

            // cambiar color del texto
            categoryNameTv.setTextColor(if(layoutPosition == selectedItem)
                ContextCompat.getColor(root.context, R.color.colorSelected)
            else
                ContextCompat.getColor(root.context, R.color.grey))

            categoryItem.setOnClickListener {
                notifyItemChanged(selectedItem)
                selectedItem = layoutPosition
                notifyItemChanged(selectedItem)
                listener.onClick(category)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(CategoryViewholderBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {

        holder.bind(categories.get(position))

        holder.itemView.isSelected = (selectedItem == position)
    }

    fun setList(newList : List<Category>) {
        categories = newList
        notifyDataSetChanged()
    }


    interface onItemClick {
        fun onClick(category: Category)
    }

}