package com.example.fooddeliveryapp.ui.components

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle

import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import com.example.fooddeliveryapp.databinding.DialogRemoveProductBinding
import com.example.fooddeliveryapp.databinding.DialogRemoveProductBinding.inflate


class ItemDeletionDialog<T>(
    val item: T,
    val titleText: String,
    val deleteButtonText: String,
    val listener: onSubmitClickListener<T>
) : DialogFragment()
{
    private lateinit var binding: DialogRemoveProductBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        binding = inflate(LayoutInflater.from(context))

        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding.root)

        binding.titleText.setText(titleText)
        binding.deleteBtn.setText(deleteButtonText)

        binding.cancelBtn.setOnClickListener {
            dismiss()
        }

        binding.deleteBtn.setOnClickListener {

            listener.onItemRemovePermanently(item)
            dismiss()
        }

        val dialog  = builder.create()
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }

    interface onSubmitClickListener<T> {

        fun onItemRemovePermanently(item: T)
    }
}



