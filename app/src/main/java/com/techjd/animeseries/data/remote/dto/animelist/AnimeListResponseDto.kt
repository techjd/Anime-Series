package com.techjd.animeseries.data.remote.dto.animelist


import com.techjd.animeseries.data.remote.dto.anime.animelist.Pagination
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnimeListResponseDto(
    @SerialName("data")
    val `data`: List<AnimeDto> = listOf(),
    @SerialName("pagination")
    val pagination: Pagination? = null
)