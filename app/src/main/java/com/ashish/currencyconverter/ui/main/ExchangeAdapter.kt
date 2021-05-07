package com.ashish.currencyconverter.ui.main

import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ashish.currencyconverter.R
import com.ashish.currencyconverter.ui.entity.ExchangeRate
import kotlinx.android.synthetic.main.exchange_list_item.view.*

class ExchangeAdapter(val exchangeList:ArrayList<ExchangeRate>, val amountEditText: EditText) : RecyclerView.Adapter<ExchangeAdapter.ExchangeViewHolder>() {

    lateinit var inflator:LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExchangeViewHolder {
            if(!this::inflator.isInitialized){
                inflator = LayoutInflater.from(parent.context)
            }
        val view = inflator.inflate(R.layout.exchange_list_item,parent,false)
        val vh = ExchangeViewHolder(view)
        amountEditText.addTextChangedListener(vh)
        return vh
    }

    override fun getItemCount(): Int {
        val s:String = ""
        return exchangeList.size

    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ExchangeViewHolder, position: Int) {
        holder.exchangeRate = exchangeList.get(position)
        holder.exchangeRate?.let {
            val txt = amountEditText.text.toString()
            if(txt.isEmpty()){
                holder.textView.text = it.currency
            }else
            holder.textView.text = it.currency+"  " + String.format("%.2f", it.rate  * txt.toDouble())
        }
    }

    class ExchangeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), TextWatcher {
        var exchangeRate:ExchangeRate? = null
        val textView:TextView = this.itemView.exchangeTextView

        @SuppressLint("SetTextI18n")
        override fun afterTextChanged(s: Editable?) {
            exchangeRate?.let {
                textView.text = it.currency +"  " + String.format("%.2f", it.rate  * s.toString().toDouble())
            }
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

    }
}