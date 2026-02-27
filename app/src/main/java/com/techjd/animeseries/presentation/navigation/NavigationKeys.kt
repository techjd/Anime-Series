package com.techjd.animeseries.presentation.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data object HomeScreen : NavKey

@Serializable
data class DetailScreen(val animeId: Int) : NavKey