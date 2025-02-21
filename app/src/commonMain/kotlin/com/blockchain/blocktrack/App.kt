package com.blockchain.blocktrack

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.rememberNavController
import com.blockchain.blocktrack.di.AppComponent
import com.blockchain.blocktrack.di.LocalAppComponent
import com.blockchain.blocktrack.ui.navigation.AppNavHost
import com.blockchain.blocktrack.ui.navigation.BlockTrackNavigationSuiteScaffold
import com.blockchain.blocktrack.ui.rememberAppState
import com.blockchain.blocktrack.ui.theme.BlockTrackTheme
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import kotlin.reflect.KClass

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(
    appComponent: AppComponent,
    exitApp: () -> Unit
) {
    CompositionLocalProvider(
        LocalAppComponent provides appComponent
    ) {
        BlockTrackTheme {
            val windowAdaptiveInfo: WindowAdaptiveInfo = currentWindowAdaptiveInfo()
            val appState = rememberAppState()
            val currentDestination = appState.currentDestination
            BlockTrackNavigationSuiteScaffold(
                navigationSuiteItems = {
                    appState.topLevelDestinations.forEach { destination ->
                        val selected = currentDestination
                            .isRouteInHierarchy(destination.baseRoute)
                        item(
                            selected = selected,
                            onClick = { appState.navigateToTopLevelDestination(destination) },
                            icon = {
                                Icon(
                                    imageVector = vectorResource(destination.icon),
                                    contentDescription = stringResource(destination.label),
                                )
                            },
                            selectedIcon = {
                                Icon(
                                    imageVector = vectorResource(destination.activeIcon),
                                    contentDescription = stringResource(destination.label),
                                )
                            },
                            label = {
                                Text(
                                    text = stringResource(destination.label)
                                )
                            },
                            modifier = Modifier,
                        )
                    }
                },
                windowAdaptiveInfo = windowAdaptiveInfo,
            ) {
                Scaffold(
                    containerColor = Color.Transparent,
                    contentColor = MaterialTheme.colorScheme.onBackground,
                    contentWindowInsets = WindowInsets(0, 0, 0, 0),
                    content = { paddingValues ->
                        val destination = appState.currentTopLevelDestination
                        var shouldShowTopAppBar = false
                        Column(
                            Modifier
                                .fillMaxSize()
                                .padding(paddingValues)
                                .consumeWindowInsets(paddingValues)
                                .windowInsetsPadding(
                                    WindowInsets.safeDrawing.only(
                                        WindowInsetsSides.Horizontal,
                                    ),
                                ),
                        ) {
                            if (destination != null) {
                                shouldShowTopAppBar = true
                                TopAppBar(
                                    title = {
                                        Text(
                                            text = stringResource(destination.label),
                                        )
                                    },
                                    navigationIcon = {
                                        Icon(
                                            imageVector = vectorResource(destination.icon),
                                            contentDescription = stringResource(destination.label),
                                        )
                                    },
                                    actions = {}
                                )
                            }
                            Box(
                                // Workaround for https://issuetracker.google.com/338478720
                                modifier = Modifier.consumeWindowInsets(
                                    if (shouldShowTopAppBar) {
                                        WindowInsets.safeDrawing.only(WindowInsetsSides.Top)
                                    } else {
                                        WindowInsets(0, 0, 0, 0)
                                    },
                                ),
                            ) {
                                AppNavHost(
                                    appState = appState
                                )
                            }
                        }
                    }
                )
            }
        }
    }
}

private fun NavDestination?.isRouteInHierarchy(route: KClass<*>) =
    this?.hierarchy?.any {
        it.hasRoute(route)
    } == true