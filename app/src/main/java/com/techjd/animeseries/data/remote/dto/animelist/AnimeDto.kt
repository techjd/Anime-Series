package com.techjd.animeseries.data.remote.dto.animelist


import com.techjd.animeseries.data.remote.dto.anime.animelist.Licensor
import com.techjd.animeseries.data.remote.dto.anime.animelist.Studio
import com.techjd.animeseries.data.remote.dto.anime.animelist.Theme
import com.techjd.animeseries.data.remote.dto.anime.animelist.Title
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnimeDto(
    @SerialName("background")
    val background: String? = "",
    @SerialName("episodes")
    val episodes: Int? = 0,
    @SerialName("favorites")
    val favorites: Int? = 0,
    @SerialName("genres")
    val genres: List<Genre> = listOf(),
    @SerialName("images")
    val images: Images? = Images(),
    @SerialName("licensors")
    val licensors: List<Licensor>? = listOf(),
    @SerialName("mal_id")
    val malId: Int? = 0,
    @SerialName("rank")
    val rank: Int? = 0,
    @SerialName("rating")
    val rating: String? = "",
    @SerialName("score")
    val score: Double? = 0.0,
    @SerialName("scored_by")
    val scoredBy: Int? = 0,
    @SerialName("season")
    val season: String? = "",
    @SerialName("source")
    val source: String? = "",
    @SerialName("status")
    val status: String? = "",
    @SerialName("studios")
    val studios: List<Studio>? = listOf(),
    @SerialName("synopsis")
    val synopsis: String?,
    @SerialName("themes")
    val themes: List<Theme>? = listOf(),
    @SerialName("title")
    val title: String? = "",
    @SerialName("title_english")
    val titleEnglish: String? = "",
    @SerialName("title_japanese")
    val titleJapanese: String? = "",
    @SerialName("title_synonyms")
    val titleSynonyms: List<String>? = listOf(),
    @SerialName("titles")
    val titles: List<Title>? = listOf(),
    @SerialName("trailer")
    val trailer: Trailer?,
    @SerialName("type")
    val type: String? = "",
    @SerialName("url")
    val url: String? = "",
)