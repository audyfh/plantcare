package com.example.plantcare.presentation.home.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.plantcare.presentation.home.HomeViewModel
import com.example.plantcare.presentation.home.comps.HomeSearchBar
import com.example.plantcare.presentation.plantlist.comps.PlantCard
import com.example.plantcare.ui.theme.PrimaryGreen
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    query: String,
    navigateBack: () -> Unit,
    navigateDetail: (Int) -> Unit
) {
    val viewModel: SearchViewModel = koinViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()
    val searchQuery = state.searchQuery

    LaunchedEffect(Unit) {
        viewModel.searchPlants(query)
        viewModel.updateSearchQuery(query)
    }
    when {
       state.isLoading -> {
           Box(
               modifier = modifier
                   .fillMaxSize()
                   .background(Color.White),
               contentAlignment = Alignment.Center
           ){
               CircularProgressIndicator(color = PrimaryGreen)
           }

       }
        state.errorMsg != null -> {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ){
                Text(
                    "${state.errorMsg}",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
        state.plants.isNotEmpty() -> {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(14.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = PrimaryGreen,
                        modifier = Modifier
                            .size(24.dp)
                            .clickable { navigateBack() }
                    )
                    Spacer(Modifier.width(6.dp))
                    HomeSearchBar(
                        query = searchQuery,
                        onQueryChange = {viewModel.updateSearchQuery(it)},
                        onSearch = {viewModel.searchPlants(searchQuery)}
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(state.plants){
                        PlantCard(
                            plant = it,
                        ) {
                            navigateDetail(it)
                        }
                    }
                }
            }
        }
        else -> {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ){
                Text(
                    "There is no plant",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }

}