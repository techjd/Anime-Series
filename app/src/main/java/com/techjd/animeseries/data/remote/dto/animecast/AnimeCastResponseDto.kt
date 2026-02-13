package com.techjd.animeseries.data.remote.dto.animecast


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnimeCastResponseDto(
    @SerialName("data")
    val `data`: List<Data>? = listOf()
)