package com.blockchain.blocktrack.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.blockchain.blocktrack.ui.AppState
import com.blockchain.blocktrack.ui.home.HomeRoute
import com.blockchain.blocktrack.ui.home.HomeScreen
import com.blockchain.blocktrack.ui.search.SearchRoute
import com.blockchain.blocktrack.ui.search.SearchScreen

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
        composable<SearchRoute> {
            SearchScreen()
        }
    }
}