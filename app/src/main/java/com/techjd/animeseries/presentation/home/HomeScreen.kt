package com.techjd.animeseries.presentation.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onAnimeClick: (animeId: Int) -> Unit,
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    val animeItems = viewModel.topAnime.collectAsLazyPagingItems()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(animeItems.itemCount) {
        Log.d("ItemCount", "HomeScreen: ${animeItems.itemCount}")
    }

    Scaffold(
        topBar = {
            Column {
                if (uiState.isOffline) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFFFF9800))
                            .padding(8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        if (animeItems.itemCount == 0) {
                            Text(
                                text = "📡 Offline Mode - No Cached Data Available",
                                color = Color.White,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        } else {
                            Text(
                                text = "📡 Offline Mode - Showing Cached Data",
                                color = Color.White,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(12.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                this.items(
                    count = animeItems.itemCount,
                    key = animeItems.itemKey { it.animeId }
                ) { index ->
                    val item = animeItems[index]
                    if (item != null) {
                        AnimeListItem(anime = item, onAnimeClick = {
                            onAnimeClick(item.animeId)
                        })
                    } else {
                        AnimeRowPlaceholder()
                    }
                }

                item {
                    when (val append = animeItems.loadState.append) {
                        is LoadState.Loading -> {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                horizontalArrangement = Arrangement.Center
                            ) { CircularProgressIndicator() }
                        }

                        is LoadState.Error -> {
                            ErrorRow(
                                message = append.error.message ?: "Something went wrong",
                                onRetry = { animeItems.retry() }
                            )
                        }

                        else -> Unit
                    }
                }
            }

            when (val refresh = animeItems.loadState.refresh) {
                is LoadState.Loading -> {
                    if (animeItems.itemCount == 0) {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }
                }

                is LoadState.Error -> {
                    if (animeItems.itemCount == 0) {
                        FullScreenError(
                            message = refresh.error.message ?: "Failed to load",
                            onRetry = { animeItems.retry() }
                        )
                    } else {
                        // list already has items, just show a retry option - not doing it
                        // due to time constraints, but ideally we should show a small banner/snackbar with retry option
                    }
                }

                else -> Unit
            }
        }
    }
}

@Composable
fun AnimeRowPlaceholder() {
    Card(modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .height(80.dp)
                .padding(12.dp)
        )
    }
}

@Composable
fun ErrorRow(message: String, onRetry: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = message, style = MaterialTheme.typography.bodySmall)
        TextButton(onClick = onRetry) { Text("Retry") }
    }
}

@Composable
fun FullScreenError(message: String, onRetry: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(message, textAlign = TextAlign.Center)
        Spacer(Modifier.height(12.dp))
        Button(onClick = onRetry) { Text("Retry") }
    }
}
