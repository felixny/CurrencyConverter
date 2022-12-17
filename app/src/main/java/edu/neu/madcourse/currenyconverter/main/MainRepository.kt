package edu.neu.madcourse.currenyconverter.main

import edu.neu.madcourse.currenyconverter.data.models.CurrencyResponse
import edu.neu.madcourse.currenyconverter.util.Resource

interface MainRepository {

    suspend fun getRates(base: String) : Resource<CurrencyResponse>
}