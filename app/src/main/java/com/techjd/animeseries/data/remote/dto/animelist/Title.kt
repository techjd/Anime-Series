package com.techjd.animeseries.data.remote.dto.anime.animelist


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Title(
    @SerialName("title")
    val title: String? = null,
    @SerialName("type")
    val type: String? = null
)