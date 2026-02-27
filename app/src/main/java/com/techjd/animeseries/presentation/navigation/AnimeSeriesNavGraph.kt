package com.techjd.animeseries.presentation.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import com.techjd.animeseries.presentation.detail.AnimeDetailScreen
import com.techjd.animeseries.presentation.detail.AnimeDetailViewModel
import com.techjd.animeseries.presentation.home.HomeScreen

@Composable
fun AnimeAppNavGraph(
    modifier: Modifier = Modifier,
) {
    val backstack = remember { mutableStateListOf<Any>(HomeScreen) }

    NavDisplay(
        modifier = modifier,
        backStack = backstack,
        onBack = { backstack.removeLastOrNull() },
        entryProvider = { key ->
            when (key) {
                HomeScreen -> NavEntry(key) {
                    HomeScreen(
                        onAnimeClick = { animeId ->
                            backstack.add(DetailScreen(animeId))
                        }
                    )
                }

                is DetailScreen -> NavEntry(key) {
                    AnimeDetailScreen(
                        onBackClick = {
                            backstack.removeLastOrNull()
                        },
                        viewModel = hiltViewModel<AnimeDetailViewModel, AnimeDetailViewModel.Factory>(
                            creationCallback = { factory ->
                                factory.create(key)
                            }
                        )
                    )
                }

                else -> NavEntry(Unit) {
                    Text("Unknown Route")
                }
            }
        }
    )
}
