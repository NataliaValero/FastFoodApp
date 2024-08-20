package com.example.fooddeliveryapp.utils

import android.app.Activity
import android.view.Gravity
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.fooddeliveryapp.R

fun Toast.showCustomToast(message: String, activity: Activity) {
    val inflater = activity.layoutInflater
    val layout = inflater.inflate(R.layout.toast_container, activity.findViewById(R.id.toast_container))

    val textView = layout.findViewById<TextView>(R.id.textToast)
    textView.text = message

    val toast = Toast(activity)
    toast.duration = Toast.LENGTH_SHORT
    toast.view = layout
    toast.show()
}

