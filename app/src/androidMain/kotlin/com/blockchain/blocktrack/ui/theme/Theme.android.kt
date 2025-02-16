package com.blockchain.blocktrack.ui.theme

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import com.blockchain.blocktrack.utils.KmpContext
import com.blockchain.blocktrack.utils.ThemePrefUtil

@RequiresApi(Build.VERSION_CODES.S)
actual fun KmpContext.generateColorScheme(
    nightModeValue: Int,
    dynamicColor: Boolean,
    lightScheme: ColorScheme,
    darkScheme: ColorScheme
): ColorScheme = if (dynamicColor) {
    when (nightModeValue) {
        ThemePrefUtil.AMOLED -> dynamicDarkColorScheme(this).toAmoled()
        ThemePrefUtil.DARK -> dynamicDarkColorScheme(this)
        else -> dynamicLightColorScheme(this)
    }
} else {
    when (nightModeValue) {
        ThemePrefUtil.AMOLED -> darkScheme.toAmoled()
        ThemePrefUtil.DARK -> darkScheme
        else -> lightScheme
    }
}