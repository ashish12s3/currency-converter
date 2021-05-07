package com.ashish.currencyconverter.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ashish.currencyconverter.R
import com.ashish.currencyconverter.data.api.ApiHelperImpl
import com.ashish.currencyconverter.data.api.RetrofitBuilder
import com.ashish.currencyconverter.data.model.CurrencyExchangeRateList
import com.ashish.currencyconverter.data.util.Resource
import com.ashish.currencyconverter.data.util.Status
import com.ashish.currencyconverter.ui.base.ViewModelFactory
import com.ashish.currencyconverter.utils.toExchangeMap
import kotlinx.android.synthetic.main.fragment_exchange_rate.*
import java.util.*


private const val ARG_CURRENCY = "currency"

/**
 * A simple [Fragment] subclass.
 * Use the [ExchangeRateFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ExchangeRateFragment : Fragment() {
    private lateinit var viewModel: MainViewModel
    private var currency: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            currency = it.getString(ARG_CURRENCY)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_exchange_rate, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        context?.let {
            exchangeRecyclerView.layoutManager = GridLayoutManager(it,3)
        }
        viewModel = ViewModelProvider(this, ViewModelFactory(ApiHelperImpl(RetrofitBuilder.apiService))).get(MainViewModel::class.java)
        viewModel.getExchangeRate().observe(this, Observer<Resource<CurrencyExchangeRateList>> {
            if (it.status == Status.SUCCESS){
                it.data?.let {
                    exchangeRecyclerView.adapter = ExchangeAdapter(it.quotes.toExchangeMap(it.source),amountEditText)

                }

            }
        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param currency Parameter 1.
         * @return A new instance of fragment ExchangeRateFragment.
         */
        @JvmStatic
        fun newInstance(currency: String) =
            ExchangeRateFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_CURRENCY, currency)
                }
            }
    }
}