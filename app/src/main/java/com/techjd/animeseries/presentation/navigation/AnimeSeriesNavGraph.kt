package com.techjd.animeseries.presentation.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
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
    val listDetailStrategy = rememberListDetailSceneStrategy<NavKey>()

    NavDisplay(
        modifier = modifier,
        backStack = backstack,
        onBack = { backstack.removeLastOrNull() },
        sceneStrategies = listOf(listDetailStrategy),
        entryProvider = entryProvider {
            entry<HomeScreen>(
                metadata = ListDetailScene.listPane()
            ) {
                HomeScreen(
                    onAnimeClick = { animId ->
                        backstack.addDetail(DetailScreen(animId))
                    }
                )
            }
            entry<DetailScreen>(
                metadata = ListDetailScene.detailPane()
            ) { key ->
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

private fun NavBackStack<NavKey>.addDetail(detailRoute: DetailScreen) {

    // Remove any existing detail routes before adding this detail route.
    // In certain scenarios, such as when multiple detail panes can be shown at once, it may
    // be desirable to keep existing detail routes on the back stack.
    removeIf { it is DetailScreen }
    add(detailRoute)
}