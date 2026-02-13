package com.techjd.animeseries.data.remote.dto.anime.animelist


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Items(
    @SerialName("count")
    val count: Int? = null,
    @SerialName("per_page")
    val perPage: Int? = null,
    @SerialName("total")
    val total: Int? = null
)