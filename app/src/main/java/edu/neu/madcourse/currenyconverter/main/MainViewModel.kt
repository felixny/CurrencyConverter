package edu.neu.madcourse.currenyconverter.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.neu.madcourse.currenyconverter.util.DispatcherProvider
import edu.neu.madcourse.currenyconverter.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    private val repository: MainRepository,
    private val dispatchers: DispatcherProvider

): ViewModel() {

    sealed class CurrencyEvent{
        class Success(val resultText: String): CurrencyEvent()
        class Failure(val errorText: String): CurrencyEvent()
        object Loading : CurrencyEvent()
        object Empty : CurrencyEvent()
    }

    private val _conversion = MutableStateFlow<CurrencyEvent>(CurrencyEvent.Empty)
    val conversion: StateFlow<CurrencyEvent> = _conversion

    fun convert(
        amountStr: String,
        fromCurrency: String,
        toCurrency: String
    ){
        val fromAmount = amountStr.toFloatOrNull()
        if (fromAmount == null){
            _conversion.value = CurrencyEvent.Failure("Not a valid amount")
            return
        }

        viewModelScope.launch(dispatchers.io){
            _conversion.value = CurrencyEvent.Loading
            when (val ratesResponse = repository.getRates(fromCurrency)){
                is Resource.Error -> _conversion.value = CurrencyEvent.Failure(ratesResponse.message!!)
            }
        }
    }
}