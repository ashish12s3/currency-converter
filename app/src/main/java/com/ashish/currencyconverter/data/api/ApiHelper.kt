package com.ashish.currencyconverter.data.api

import com.ashish.currencyconverter.data.model.CurrencyExchangeRateList
import com.ashish.currencyconverter.data.model.CurrencyList

interface ApiHelper {
    suspend fun getCurrency(): CurrencyList
    suspend fun getExchangeRate():CurrencyExchangeRateList
}