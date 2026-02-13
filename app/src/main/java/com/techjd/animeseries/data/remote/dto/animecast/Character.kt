package com.techjd.animeseries.data.remote.dto.animecast


import com.techjd.animeseries.data.remote.dto.animelist.Images
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Character(
    @SerialName("images")
    val images: Images? = null,
    @SerialName("mal_id")
    val malId: Int? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("url")
    val url: String? = null
)