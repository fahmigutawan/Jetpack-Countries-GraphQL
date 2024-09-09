package com.example.countriesgraphql.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.EmojiSupportMatch
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.countriesgraphql.utils.Resource
import com.example.countriesgraphql.utils.getFlagEmoji

@Composable
fun CountryListContent(modifier: Modifier = Modifier) {
    val viewModel = viewModel<CountryListViewModel>()
    val countries = viewModel.countries.collectAsState()
    val country = viewModel.country.collectAsState()

    Column {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .padding(top = 24.dp, bottom = 12.dp),
            value = viewModel.searchInput.value,
            onValueChange = {
                viewModel.searchInput.value = it
            },
            placeholder = {
                Text(text = "Cari negara...")
            }
        )

        LazyColumn(
            modifier = modifier.fillMaxSize().weight(1f)
        ) {
            if (countries.value is Resource.Loading) {
                item {
                    Box(Modifier.fillParentMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(42.dp)
                        )
                    }
                }
            }

            if (countries.value is Resource.Success) {
                items(
                    countries
                        .value
                        .data
                        ?.countries
                        ?.filter {
                            if (viewModel.searchInput.value.isEmpty()) true
                            else it?.name?.lowercase()?.contains(viewModel.searchInput.value) ?: true
                        } ?: listOf()
                ) { item ->
                    item?.let { itemNotNull ->
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    //TODO
                                }
                        ) {
                            Row(
                                modifier = Modifier.padding(vertical = 24.dp, horizontal = 24.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                Text(
                                    text = itemNotNull.emoji ?: "-",
                                    fontSize = 32.sp,
                                )
                                Text(text = itemNotNull.name ?: "...", fontSize = 16.sp)
                            }
                            HorizontalDivider(
                                thickness = 1.dp,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        }
                    }
                }
            }
        }
    }

}