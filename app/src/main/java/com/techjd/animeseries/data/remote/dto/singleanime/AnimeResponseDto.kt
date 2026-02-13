package com.techjd.animeseries.data.remote.dto.singleanime

import com.techjd.animeseries.data.remote.dto.animelist.AnimeDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnimeResponseDto(
    @SerialName("data")
    val animeDto: AnimeDto? = null
)
