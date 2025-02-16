package com.blockchain.blocktrack

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.window.ComposeUIViewController
import coil3.SingletonImageLoader
import com.blockchain.blocktrack.di.AppComponent
import com.blockchain.blocktrack.di.create
import com.blockchain.blocktrack.utils.KmpContext
import com.blockchain.blocktrack.utils.LocalKmpContext
import platform.UIKit.UIViewController

private object IosContext : KmpContext()

fun MainViewController(): UIViewController {
    val appComponent = AppComponent.Companion.create(IosContext)

    SingletonImageLoader.setSafe {
        appComponent.provideImageLoader()
    }

    val finishApp = {}
    return ComposeUIViewController {
        CompositionLocalProvider(
            LocalKmpContext provides IosContext
        ) {
            App(appComponent, finishApp)
        }
    }
}