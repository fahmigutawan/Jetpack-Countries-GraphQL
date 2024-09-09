package com.example.countriesgraphql.data

import com.example.countriesgraphql.utils.getResponse
import javax.inject.Inject

class Repository @Inject constructor(
    private val source: Source
) {
    fun getCountryByCode(code:String) = getResponse {
        source.getCountryByCode(code)
    }

    fun getCountries() = getResponse {
        source.getCountries()
    }
}