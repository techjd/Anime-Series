package com.techjd.animeseries.utils

fun extractVideoIdFromYoutubeUrl(videoUrl: String?): String? {
    if (videoUrl == null) return null
    val regex = Regex("""embed/([a-zA-Z0-9_-]+)""")
    return regex.find(videoUrl)?.groupValues?.get(1)
}