package com.blockchain.blocktrack.utils

import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatDelegate
import coil3.PlatformContext
import com.blockchain.blocktrack.utils.ThemePrefUtil.AMOLED
import com.blockchain.blocktrack.utils.ThemePrefUtil.DARK
import com.blockchain.blocktrack.utils.ThemePrefUtil.LIGHT
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings
import okio.Path
import okio.Path.Companion.toPath
import java.io.File

actual fun supportsDynamicTheming(): Boolean =
    Build.VERSION.SDK_INT >= Build.VERSION_CODES.S

actual typealias KmpContext = Context

actual val KmpContext.pref: Settings
    get() = SharedPreferencesSettings(
        applicationContext.getSharedPreferences(
            applicationContext.packageName + "_preferences",
            Context.MODE_PRIVATE
        )
    )

actual fun KmpContext.setDefaultNightMode(mode: Int) {
    AppCompatDelegate.setDefaultNightMode(
        when (mode) {
            LIGHT -> AppCompatDelegate.MODE_NIGHT_NO
            AMOLED, DARK -> AppCompatDelegate.MODE_NIGHT_YES
            else -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
        }
    )
}

actual val KmpContext.dataStoreDir: Path
    get() = File(applicationContext.filesDir, "datastore").path.toPath()
actual val KmpContext.coilContext: PlatformContext get() = this
actual val KmpContext.imageCacheDir: Path get() = cacheDir.path.toPath().resolve("image_cache")