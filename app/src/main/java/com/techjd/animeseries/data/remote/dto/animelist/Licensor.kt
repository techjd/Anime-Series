package com.techjd.animeseries.data.remote.dto.anime.animelist


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Licensor(
    @SerialName("mal_id")
    val malId: Int? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("type")
    val type: String? = null,
    @SerialName("url")
    val url: String? = null
)