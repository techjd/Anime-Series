package com.techjd.animeseries.data.utils

import com.techjd.animeseries.data.remote.dto.animelist.Images

fun Images?.bestPoster(): String? {
    return listOf(
        this?.webp?.largeImageUrl,
        this?.webp?.imageUrl,
        this?.webp?.smallImageUrl,
        this?.jpg?.largeImageUrl,
        this?.jpg?.imageUrl,
        this?.jpg?.smallImageUrl
    ).firstOrNull { !it.isNullOrBlank() }
}