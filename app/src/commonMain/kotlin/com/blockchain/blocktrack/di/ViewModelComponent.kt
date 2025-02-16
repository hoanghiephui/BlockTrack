package com.blockchain.blocktrack.di

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.KmpComponentCreate

@Component
abstract class ViewModelComponent(
    @Component val appComponent: AppComponent
) {
    companion object
}

@KmpComponentCreate
expect fun ViewModelComponent.Companion.create(app: AppComponent): ViewModelComponent

val LocalAppComponent = staticCompositionLocalOf<AppComponent> { error("no AppComponent") }

@Composable
internal fun <T> injectViewModel(key: String, block: ViewModelComponent.() -> T): T {
    val app = LocalAppComponent.current
    return remember(key) { ViewModelComponent.create(app).block() }
}