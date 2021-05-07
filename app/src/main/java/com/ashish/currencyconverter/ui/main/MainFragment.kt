package com.ashish.currencyconverter.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import com.ashish.currencyconverter.R
import com.ashish.currencyconverter.data.api.ApiHelperImpl
import com.ashish.currencyconverter.data.api.RetrofitBuilder
import com.ashish.currencyconverter.data.model.CurrencyList
import com.ashish.currencyconverter.data.util.Resource
import com.ashish.currencyconverter.data.util.Status
import com.ashish.currencyconverter.ui.base.ViewModelFactory
import com.ashish.currencyconverter.utils.keyList
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment(), AdapterView.OnItemClickListener {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        currencyAutoCompleteTextView.onItemClickListener = this
        viewModel = ViewModelProvider(this, ViewModelFactory(ApiHelperImpl(RetrofitBuilder.apiService))).get(MainViewModel::class.java)
        viewModel.getCurrency().observe(this,
            Observer<Resource<CurrencyList>> {
               when(it.status){
                   Status.SUCCESS ->{
                       progress_circular.visibility = View.GONE
                       currencyAutoCompleteTextView.visibility = View.VISIBLE
                       it.data?.let {
                           val adapter = if (context != null){
                               ArrayAdapter<String>(context!!, android.R.layout.simple_dropdown_item_1line,it.currencies.keyList())
                           }else{
                               null
                           }
                           adapter?.let {
                               currencyAutoCompleteTextView.setAdapter(it)
                           }
                       }
                   }
                   Status.LOADING ->{
                       progress_circular.visibility = View.VISIBLE
                   }
                   Status.ERROR->{
                       progress_circular.visibility = View.GONE
                       message.text = it.message
                       message.visibility = View.VISIBLE
                   }
               }
            })


    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val item = (parent?.adapter as ArrayAdapter<String>).getItem(position)
        item?.let {
            val fragment = ExchangeRateFragment.newInstance(it)
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.container,fragment)?.commit()
        }
    }


}