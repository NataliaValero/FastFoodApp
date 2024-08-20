package com.example.fooddeliveryapp.data.repository

interface SharedPreferencesRepository {

    fun isFirstTimeAppLaunch() : Boolean
    fun appAlreadyLaunched()
}