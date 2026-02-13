package com.techjd.animeseries.data.remote.dto.animelist


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Images(
    @SerialName("jpg")
    val jpg: Jpg? = null,
    @SerialName("webp")
    val webp: Webp? = null
)