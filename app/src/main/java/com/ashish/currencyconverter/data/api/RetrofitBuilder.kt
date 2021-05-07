package com.ashish.currencyconverter.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    private const val BASE_URL = "http://api.currencylayer.com/"

    const val ACCESS_KEY = "0f2b4743e8b419cb4b24ad8407281e4d"

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build() //Doesn't require the adapter
    }

    val apiService = getRetrofit().create(ApiService::class.java)
}