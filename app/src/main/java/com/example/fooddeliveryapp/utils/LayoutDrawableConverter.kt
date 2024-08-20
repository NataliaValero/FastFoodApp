package com.example.fooddeliveryapp.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.fooddeliveryapp.R

class LayoutDrawableConverter {

    fun convertLayoutToDrawableWithQuantity(context: Context, layoutResId: Int, quantity: Int) : BitmapDrawable {
        val inflater = LayoutInflater.from(context)
        val customLayout = inflater.inflate(layoutResId, null) as ViewGroup


        // Accede al textView en el layout personalizado
        val quantityTv = customLayout.findViewById<TextView>(R.id.quantity)

        if(quantity > 0) {
            quantityTv.visibility = View.VISIBLE
            quantityTv.setText(quantity.toString())


            if(quantity>99) {
                quantityTv.setText("99+")
            }
        }




        // Medidas específicas basadas en el diseño
        val widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(200, View.MeasureSpec.EXACTLY)
        val heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(200, View.MeasureSpec.EXACTLY)

        // Medir y colocar el layout personalizado
        customLayout.measure(widthMeasureSpec, heightMeasureSpec)
        customLayout.layout(0, 0, customLayout.measuredWidth, customLayout.measuredHeight)

        // Convertir el layout personalizado a bitmap
        val bitmap = Bitmap.createBitmap(
            customLayout.width,
            customLayout.height,
            Bitmap.Config.ARGB_8888
        )

        val canvas = Canvas(bitmap)
        customLayout.draw(canvas)

        // Crea un drawab;e a partir del bitmap y lo devuelve
        return BitmapDrawable(context.resources, bitmap)
    }
}