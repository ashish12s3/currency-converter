package com.ashish.currencyconverter.utils

import android.content.Context
import android.content.SharedPreferences

object PrefUtil {
    private const val NAME = "pref"
    private var prefrence: SharedPreferences? =null

   fun initialize(context: Context){
       context.getSharedPreferences(NAME,Context.MODE_PRIVATE)
   }

   fun getString(key:String , defValue: String = ""):String {
       prefrence?.let {
           return it.getString(key,defValue) ?: defValue
       }
       return defValue
   }

   fun putString(key: String,value:String){
       prefrence?.edit()?.putString(key,value)?.apply()
   }


}