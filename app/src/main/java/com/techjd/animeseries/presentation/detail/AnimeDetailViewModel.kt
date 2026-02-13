package com.techjd.animeseries.presentation.detail

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techjd.animeseries.domain.AnimeRepository
import com.techjd.animeseries.domain.models.Anime
import com.techjd.animeseries.utils.ConnectivityObserver
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


data class AnimeDetailUiState(
    val anime: Anime? = null,
    val isLoading: Boolean = true,
    val error: String? = null,
    val isOffline: Boolean = false
)

@HiltViewModel
class AnimeDetailViewModel @Inject constructor(
    private val connectivityObserver: ConnectivityObserver,
    private val repository: AnimeRepository,
    savedStateHandle: SavedStateHandle,
): ViewModel() {

    val animeId: Int = savedStateHandle.get<Int>("animeId") ?: -1

    private val _uiState = MutableStateFlow(AnimeDetailUiState())
    val uiState: StateFlow<AnimeDetailUiState> = _uiState.asStateFlow()

    init {
        if (animeId == -1) {
            _uiState.value = AnimeDetailUiState(
                anime = null,
                isLoading = false,
                error = "Invalid anime ID"
            )
        } else {
            startObservingConnectivity()
            observeAnimeDetails(animeId)
            fetchAnimeCast(animeId)
        }
    }

    private fun startObservingConnectivity() {
        _uiState
            .update {
                it.copy(isOffline = !connectivityObserver.isCurrentlyConnected())
            }
        connectivityObserver
            .isConnected
            .onEach { isConnected ->
                _uiState.update {
                    it.copy(isOffline = !isConnected)
                }
            }.launchIn(viewModelScope)
    }

    private fun observeAnimeDetails(animeId: Int) {
        repository
            .getAnimeDetails(animeId)
            .map {
                AnimeDetailUiState(
                    anime = it,
                    isLoading = false,
                    error = null
                )
            }.onEach {
                _uiState.update { currentState ->
                    currentState.copy(
                        anime = it.anime,
                        isLoading = it.isLoading,
                        error = it.error
                    )
                }
            }.launchIn(viewModelScope)
    }

    fun fetchAnimeCast(animeId: Int) {
        viewModelScope.launch {
            repository.getAnimeCast(animeId)
            // silently ignore the error that anime cast is failed
        }
    }
}