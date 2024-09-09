package com.example.countriesgraphql.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun CountryListContent(modifier: Modifier = Modifier) {
    val viewModel = viewModel<CountryListViewModel>()
}