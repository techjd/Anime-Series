package com.techjd.animeseries

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph
import com.techjd.animeseries.presentation.navigation.AnimeAppNavGraph
import com.techjd.animeseries.ui.theme.AnimeSeriesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AnimeSeriesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AnimeAppNavGraph(modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                    )
                }
            }
        }
    }
}