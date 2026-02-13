package com.techjd.animeseries.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.techjd.animeseries.domain.AnimeRepository
import com.techjd.animeseries.utils.ConnectivityObserver
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class HomeScreenUiState(
    val isOffline: Boolean = true
)

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repository: AnimeRepository,
    private val connectivityObserver: ConnectivityObserver
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeScreenUiState())
    val uiState: StateFlow<HomeScreenUiState> = _uiState.asStateFlow()

    init {
        startObservingConnectivity()
    }

    private fun startObservingConnectivity() {
        connectivityObserver
            .isConnected
            .onEach { isConnected ->
                _uiState.update {
                    it.copy(isOffline = !isConnected)
                }
            }.launchIn(viewModelScope)
    }

    val topAnime = repository
        .getTopAnime()
        .cachedIn(viewModelScope)
}