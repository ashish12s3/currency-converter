package com.ashish.currencyconverter.data.api

import com.ashish.currencyconverter.data.model.CurrencyExchangeRateList
import com.ashish.currencyconverter.data.model.CurrencyList
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("list?format=1")
    suspend fun getCurrency(@Query("access_key") accessKey:String):CurrencyList

    @GET("live?format=1")
    suspend fun getExchangeRate(@Query("access_key") accessKey:String): CurrencyExchangeRateList

}