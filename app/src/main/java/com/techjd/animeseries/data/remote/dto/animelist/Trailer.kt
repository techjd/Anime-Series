package com.techjd.animeseries.data.remote.dto.animelist


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Trailer(
    @SerialName("embed_url")
    val embedUrl: String?
)