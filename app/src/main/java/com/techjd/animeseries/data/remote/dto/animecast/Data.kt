package com.techjd.animeseries.data.remote.dto.animecast


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Data(
    @SerialName("character")
    val character: Character? = null
)