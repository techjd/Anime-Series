package com.techjd.animeseries.data.remote.dto.anime.animelist


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Aired(
    @SerialName("from")
    val from: String? = null,
    @SerialName("prop")
    val prop: Prop? = null,
    @SerialName("string")
    val string: String? = null,
    @SerialName("to")
    val to: String? = null
)