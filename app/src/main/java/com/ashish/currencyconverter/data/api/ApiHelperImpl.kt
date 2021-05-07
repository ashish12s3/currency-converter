package com.ashish.currencyconverter.data.api

class ApiHelperImpl(private val apiService: ApiService):ApiHelper {

        override suspend fun getCurrency() = apiService.getCurrency(RetrofitBuilder.ACCESS_KEY)
        override suspend fun getExchangeRate() = apiService.getExchangeRate(RetrofitBuilder.ACCESS_KEY)
}