package com.blockchain.blocktrack.utils

import androidx.compose.runtime.staticCompositionLocalOf
import com.russhwolf.settings.Settings
import okio.Path

expect fun supportsDynamicTheming(): Boolean

expect abstract class KmpContext
val LocalKmpContext = staticCompositionLocalOf<KmpContext> { error("no KmpContext") }

expect val KmpContext.pref: Settings

expect fun KmpContext.setDefaultNightMode(mode: Int)
expect val KmpContext.dataStoreDir: Path