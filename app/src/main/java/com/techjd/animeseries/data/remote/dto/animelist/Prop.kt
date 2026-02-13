package com.techjd.animeseries.data.remote.dto.anime.animelist


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Prop(
    @SerialName("from")
    val from: From? = null,
    @SerialName("to")
    val to: To? = null
)