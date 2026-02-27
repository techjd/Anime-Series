package com.techjd.animeseries.presentation.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.techjd.animeseries.presentation.detail.AnimeDetailScreen
import com.techjd.animeseries.presentation.detail.AnimeDetailViewModel
import com.techjd.animeseries.presentation.home.HomeScreen

@Composable
fun AnimeAppNavGraph(
    modifier: Modifier = Modifier,
) {
    val backstack = rememberNavBackStack(HomeScreen)

    NavDisplay(
        modifier = modifier,
        backStack = backstack,
        onBack = { backstack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<HomeScreen> {
                HomeScreen(
                    onAnimeClick = { animId ->
                        backstack.add(DetailScreen(animId))
                    }
                )
            }
            entry<DetailScreen> { key ->
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
        }
    )
}
