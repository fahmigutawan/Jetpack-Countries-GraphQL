package com.example.countriesgraphql.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countriesgraphql.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryListViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {
    fun getCountryByCode(code:String) {
        viewModelScope.launch {
            repository.getCountryByCode(code).collect{

            }
        }
    }
}