package com.ashish.currencyconverter.data.repository

import com.ashish.currencyconverter.data.api.ApiHelper
import com.ashish.currencyconverter.data.model.CurrencyList
import com.ashish.currencyconverter.utils.PrefUtil
import com.google.gson.Gson

class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun getCurrency(): CurrencyList {
        val prefStr = PrefUtil.getString("");
        if(prefStr.isEmpty()){
            val cl = apiHelper.getCurrency()
            PrefUtil.putString("", Gson().toJson(cl))
            return cl
        }else{
         return Gson().fromJson(prefStr,CurrencyList::class.java)
        }
    }


    suspend fun getExchangeRate() = apiHelper.getExchangeRate()
}