package com.techjd.animeseries.data.remote.dto.anime.animelist


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Pagination(
    @SerialName("current_page")
    val currentPage: Int? = null,
    @SerialName("has_next_page")
    val hasNextPage: Boolean? = null,
    @SerialName("items")
    val items: Items? = null,
    @SerialName("last_visible_page")
    val lastVisiblePage: Int? = null
)