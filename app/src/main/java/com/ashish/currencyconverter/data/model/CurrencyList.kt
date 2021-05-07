package com.ashish.currencyconverter.data.model

import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName



data class CurrencyList (
    @SerializedName("success")
    val isSuccess: Boolean,
    @SerializedName("terms")
    val terms:String,
    @SerializedName("privacy")
    val privacy:String,
    @SerializedName("currencies")
    val currencies:JsonElement
)