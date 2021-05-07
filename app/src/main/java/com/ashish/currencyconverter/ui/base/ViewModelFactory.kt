package com.ashish.currencyconverter.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ashish.currencyconverter.data.api.ApiHelper
import com.ashish.currencyconverter.data.repository.MainRepository
import com.ashish.currencyconverter.ui.main.MainViewModel

class ViewModelFactory(private val apiHelper: ApiHelper):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(MainRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}