package com.blockchain.blocktrack.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.blockchain.blocktrack.ui.AppState
import com.blockchain.blocktrack.ui.home.HomeBaseRoute
import com.blockchain.blocktrack.ui.home.HomeRoute
import com.blockchain.blocktrack.ui.home.HomeScreen

@Composable
fun AppNavHost(
    appState: AppState
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = HomeRoute,
    ) {
        composable<HomeRoute> {
            HomeScreen(
                navController = navController
            )
        }
    }
}