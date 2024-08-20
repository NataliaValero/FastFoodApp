package com.example.fooddeliveryapp.data.source

import android.content.Context

class SharedPreferencesDataSource(private val context: Context) {

    private val sharedPreferences = context.getSharedPreferences("MyPreferences" , Context.MODE_PRIVATE)

    fun isFirstTimeAppLaunch() : Boolean {
        return sharedPreferences.getBoolean("isFirstTime", true)
    }

    fun appAlreadyLaunched() {
        sharedPreferences.edit().putBoolean("isFirstTime", false).apply()
    }

}