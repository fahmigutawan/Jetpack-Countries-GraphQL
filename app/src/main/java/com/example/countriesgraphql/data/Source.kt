package com.example.countriesgraphql.data

import android.hardware.biometrics.BiometricManager.Strings
import com.apollographql.apollo.ApolloClient
import com.example.countriesgraphql.CountriesQuery
import com.example.countriesgraphql.CountryQuery
import javax.inject.Inject

class Source @Inject constructor(
    private val client:ApolloClient
){
    suspend fun getCountryByCode(code:String) = client.query(CountryQuery()).execute()

    suspend fun getCountries() = client.query(CountriesQuery()).execute()
}