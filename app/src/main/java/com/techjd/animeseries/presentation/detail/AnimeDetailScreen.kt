package com.techjd.animeseries.presentation.detail

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil3.compose.AsyncImage
import coil3.compose.SubcomposeAsyncImage
import com.techjd.animeseries.domain.models.Anime
import com.techjd.animeseries.domain.models.AnimeCast
import com.techjd.animeseries.presentation.components.YouTubeTrailerPlayer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimeDetailScreen(
    viewModel: AnimeDetailViewModel = hiltViewModel<AnimeDetailViewModel>(),
    onBackClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }

            Text(
                text = "Anime Details",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            when {
                uiState.isLoading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                !uiState.error.isNullOrBlank() -> {
                    Text(
                        text = uiState.error ?: "An error occurred ! Please Try Again",
                        fontSize = 14.sp,
                        modifier = Modifier.padding(top = 8.dp, bottom = 16.dp),
                        textAlign = TextAlign.Center
                    )
                }

                uiState.anime != null -> {
                    AnimeDetailContent(
                        anime = uiState.anime!!,
                        isOffline = uiState.isOffline,
                        modifier = Modifier.fillMaxSize()
                    )
                }

                else -> Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("No anime details found")
                }
            }
        }
    }
}

@Composable
fun AnimeDetailContent(
    anime: Anime,
    isOffline: Boolean,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .padding(16.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        if (!anime.videoId.isNullOrBlank() && !isOffline) {
            YouTubeTrailerPlayer(
                videoId = anime.videoId,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            )
        } else if (anime.posterUrl.isNotBlank()) {
            SubcomposeAsyncImage(
                model = anime.posterUrl,
                contentDescription = anime.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .border(BorderStroke(1.dp, Color.LightGray))
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop,
                loading = {
                    Box(modifier = Modifier.fillMaxSize()) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .size(48.dp)
                                .align(Alignment.Center)
                        )
                    }
                },
            )
        }

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(
                text = anime.title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = anime.titleJapanese,
                fontSize = 16.sp
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            anime.numberOfEpisodes.let {
                Column {
                    Text("Episodes", fontSize = 12.sp)
                    Text(it.toString(), fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }
            }

            anime.score?.let {
                Column {
                    Text("Rating", fontSize = 12.sp)
                    Text(
                        "★ ${"%.1f".format(it)}/10",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        if (anime.genres.isNotEmpty()) {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "Genres",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = anime.genres.joinToString(", ") { it.name },
                    fontSize = 14.sp
                )
            }
        }

        anime.synopsis.let { synopsis ->
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "Synopsis",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = synopsis,
                    fontSize = 14.sp,
                    lineHeight = 20.sp
                )
            }
        }

        if (anime.cast.isNotEmpty()) {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(
                    text = "Cast",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(anime.cast.size) { index ->
                        val castMember = anime.cast[index]
                        CastItem(castMember = castMember)
                    }
                }
            }
        }
    }
}

@Composable
fun CastItem(castMember: AnimeCast) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .widthIn(max = 150.dp)
            .padding(vertical = 8.dp)
    ) {
        SubcomposeAsyncImage(
            model = castMember.imageUrl,
            contentDescription = castMember.name,
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
                .border(BorderStroke(1.dp, Color.LightGray), RoundedCornerShape(8.dp))
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop,
            error = {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text("No Image", modifier = Modifier.align(Alignment.Center))
                }
            },
            loading = {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(48.dp)
                            .align(Alignment.Center)
                    )
                }
            }
        )
        Text(
            text = castMember.name,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(horizontal = 4.dp)
        )
    }
}