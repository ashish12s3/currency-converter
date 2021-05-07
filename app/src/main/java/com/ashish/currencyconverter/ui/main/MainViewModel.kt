package com.ashish.currencyconverter.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.ashish.currencyconverter.data.repository.MainRepository
import com.ashish.currencyconverter.data.util.Resource
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class MainViewModel (private val mainRepository: MainRepository): ViewModel() {


    fun getCurrency() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getCurrency()))
        }catch (e:Exception){
            emit(Resource.error(data = null,message = e.message ?: "Exception Occured!!"))
        }
    }


    fun getExchangeRate() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getExchangeRate()))
        }catch (e:Exception){
            emit(Resource.error(data = null,message = e.message ?: "Exception Occured!!"))
        }
    }
}