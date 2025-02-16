package com.blockchain.blocktrack

import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.blockchain.blocktrack.di.AppComponent
import com.blockchain.blocktrack.di.LocalAppComponent
import com.blockchain.blocktrack.ui.composables.ReverseModalNavigationDrawer
import com.blockchain.blocktrack.ui.theme.BlockTrackTheme
import com.blockchain.blocktrack.utils.end

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
            val navController = rememberNavController()
            val scope = rememberCoroutineScope()
            val drawerState = rememberDrawerState(DrawerValue.Closed)
            val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
            var showAccountSwitchBottomSheet by remember { mutableStateOf(false) }

            ReverseModalNavigationDrawer(
                gesturesEnabled = drawerState.isOpen,
                drawerState = drawerState,
                drawerContent = {
                    ModalDrawerSheet(
                        drawerState = drawerState,
                        drawerShape = shapes.extraLarge.end(0.dp),
                    ) {

                    }
                }) {

                Scaffold(
                    bottomBar = {

                    },
                    content = { paddingValues ->

                    }
                )
            }
        }
    }
}