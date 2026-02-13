package com.techjd.animeseries.presentation.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.techjd.animeseries.presentation.detail.AnimeDetailScreen
import com.techjd.animeseries.presentation.home.HomeScreen

sealed class Screen(val route: String) {
    object HomeScreen : Screen("home_screen")
    object AnimeDetail : Screen("anime_detail/{animeId}") {
        fun createRoute(animeId: Int) = "anime_detail/$animeId"
    }
}

@Composable
fun AnimeAppNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ) {
        composable(Screen.HomeScreen.route) {
            HomeScreen(
                onAnimeClick = { animeId ->
                    navController.navigate(Screen.AnimeDetail.createRoute(animeId))
                }
            )
        }

        composable(
            route = Screen.AnimeDetail.route,
            arguments = listOf(
                navArgument("animeId") { type = NavType.IntType }
            )
        ) {
            AnimeDetailScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}
