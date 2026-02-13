package com.techjd.animeseries.data.mapper

import com.techjd.animeseries.data.local.entity.AnimeCastEntity
import com.techjd.animeseries.data.remote.dto.animecast.Data
import com.techjd.animeseries.data.remote.dto.animecast.AnimeCastResponseDto
import com.techjd.animeseries.data.utils.bestPoster

/**
 * Maps AnimeCastResponseDto to a list of AnimeCastEntity
 * @param animeId The ID of the anime to associate with the cast members
 * @return List of AnimeCastEntity
 */
fun AnimeCastResponseDto.toEntityList(animeId: Int): List<AnimeCastEntity> {
    return data?.mapNotNull { it.toEntity(animeId) } ?: emptyList()
}

fun Data.toEntity(animeId: Int): AnimeCastEntity? {
    val characterName = character?.name
    val imageUrl = character?.images?.bestPoster()

    return if (!characterName.isNullOrBlank() && character.malId != null && imageUrl != null) {
        AnimeCastEntity(
            castId = character.malId,
            animeId = animeId,
            name = characterName,
            imageUrl = imageUrl
        )
    } else {
        null
    }
}
