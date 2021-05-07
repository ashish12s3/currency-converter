package com.ashish.currencyconverter.utils

import com.ashish.currencyconverter.ui.entity.ExchangeRate
import com.google.gson.JsonElement
import org.json.JSONObject


fun JsonElement.keyList():ArrayList<String>{
    val arrayList  = ArrayList<String>()
    val obj = JSONObject(this.toString())
    val iterator = obj.keys()
    iterator.forEach {  arrayList.add(it) }
    return arrayList
}

fun JsonElement.toExchangeMap(source:String):ArrayList<ExchangeRate>{
    val arrayList  = ArrayList<ExchangeRate>()
    val obj = JSONObject(this.toString())
    val iterator = obj.keys()
    iterator.forEach {  arrayList.add(ExchangeRate(source,it.replace("USD" , "" ),obj.optDouble(it))) }
    return arrayList
}