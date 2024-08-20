package com.example.fooddeliveryapp.data.repository

import com.example.fooddeliveryapp.data.source.SharedPreferencesDataSource

class SharedPreferencesRepositoryImpl(private val sharedPreferencesDataSource: SharedPreferencesDataSource) : SharedPreferencesRepository {
    override fun isFirstTimeAppLaunch(): Boolean {
        return sharedPreferencesDataSource.isFirstTimeAppLaunch()
    }

    override fun appAlreadyLaunched() {
        sharedPreferencesDataSource.appAlreadyLaunched()
    }
}