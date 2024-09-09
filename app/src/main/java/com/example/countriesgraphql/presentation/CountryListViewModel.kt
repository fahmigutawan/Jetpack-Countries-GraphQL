package com.example.countriesgraphql.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countriesgraphql.CountriesQuery
import com.example.countriesgraphql.CountryQuery
import com.example.countriesgraphql.data.Repository
import com.example.countriesgraphql.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryListViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {
    val country = MutableStateFlow<Resource<CountryQuery.Data>>(Resource.Loading())
    val countries = MutableStateFlow<Resource<CountriesQuery.Data>>(Resource.Loading())

    fun getCountryByCode(code:String) {
        viewModelScope.launch {
            repository.getCountryByCode(code).collect{
                country.value = it
            }
        }
    }

    fun getCountries(){
        viewModelScope.launch {
            repository.getCountries().collect{
                countries.value = it
            }
        }
    }

    init {
        getCountries()
    }
}