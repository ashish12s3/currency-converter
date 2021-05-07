package com.ashish.currencyconverter

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.ashish.currencyconverter.data.api.ApiHelper
import com.ashish.currencyconverter.data.model.CurrencyExchangeRateList
import com.ashish.currencyconverter.data.model.CurrencyList
import com.ashish.currencyconverter.data.repository.MainRepository
import com.ashish.currencyconverter.data.util.Resource
import com.ashish.currencyconverter.ui.main.MainViewModel
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import java.lang.RuntimeException

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var apiHelper: ApiHelper

    @Mock
    private lateinit var currencyListObserver: Observer<Resource<CurrencyList>>

    @Mock
    private lateinit var exchangeListObserver: Observer<Resource<CurrencyExchangeRateList>>

    @Before
    fun before(){

    }

    @Test
    fun givenServerResponse200_whenFetch_currency_shouldReturnSuccess() {
        val currencyList= CurrencyList(true,"terms","privacy",JsonObject())
        testCoroutineRule.runBlockingTest {
            doReturn(currencyList)
                .`when`(apiHelper)
                .getCurrency()
            val viewModel = MainViewModel(MainRepository(apiHelper))
            apiHelper.getCurrency()
            viewModel.getCurrency().observeForever(currencyListObserver)
            verify(apiHelper).getCurrency()
            currencyListObserver.onChanged(Resource.success(currencyList))
            verify(currencyListObserver).onChanged(Resource.success(currencyList))
            viewModel.getCurrency().removeObserver(currencyListObserver)
        }

    }
    @Test
    fun givenServerResponseError_whenFetch_currency_shouldReturnError() {
        testCoroutineRule.runBlockingTest {
            val errorMessage = "Error occured!!"
            doThrow(RuntimeException(errorMessage))
                .`when`(apiHelper)
                .getCurrency()
            val viewModel = MainViewModel(MainRepository(apiHelper))
            viewModel.getCurrency().observeForever(currencyListObserver)
            verify(apiHelper).getCurrency()
            currencyListObserver.onChanged(Resource.error(data = null, message = errorMessage))
            verify(currencyListObserver).onChanged(
                Resource.error(
                    data = null,
                    message = errorMessage
                )
            )
            viewModel.getCurrency().removeObserver(currencyListObserver)
        }
    }

    @Test
    fun givenServerResponse200_whenFetch_exchangeList_shouldReturnSuccess() {
        val exchangeList= CurrencyExchangeRateList(isSuccess = true,terms = "terms",privacy = "privacy",timestamp = 7878784L,source = "USD",quotes = JsonObject())
        testCoroutineRule.runBlockingTest {
            doReturn(exchangeList)
                .`when`(apiHelper)
                .getExchangeRate()
            val viewModel = MainViewModel(MainRepository(apiHelper))
            apiHelper.getExchangeRate()
            viewModel.getExchangeRate().observeForever(exchangeListObserver)
            verify(apiHelper).getExchangeRate()
            exchangeListObserver.onChanged(Resource.success(exchangeList))
            verify(exchangeListObserver).onChanged(Resource.success(exchangeList))
            viewModel.getExchangeRate().removeObserver(exchangeListObserver)
        }
    }

    @Test
    fun givenServerResponseError_whenFetch_exchangeList_shouldReturnError() {
        testCoroutineRule.runBlockingTest {
            val errorMessage = "Error occured!!"
            doThrow(RuntimeException(errorMessage))
                .`when`(apiHelper)
                .getExchangeRate()
            val viewModel = MainViewModel(MainRepository(apiHelper))
            viewModel.getExchangeRate().observeForever(exchangeListObserver)
            exchangeListObserver.onChanged(Resource.error(data = null, message = errorMessage))
            verify(exchangeListObserver).onChanged(
                Resource.error(
                    data = null,
                    message = errorMessage
                )
            )
            viewModel.getExchangeRate().removeObserver(exchangeListObserver)
        }
    }

    @After
    fun after(){

    }
}