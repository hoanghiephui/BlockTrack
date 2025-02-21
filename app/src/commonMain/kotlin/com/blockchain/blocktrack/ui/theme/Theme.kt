package com.blockchain.blocktrack.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import com.blockchain.blocktrack.utils.KmpContext
import com.blockchain.blocktrack.utils.LocalKmpContext
import com.blockchain.blocktrack.utils.ThemePrefUtil.DARK
import com.blockchain.blocktrack.utils.ThemePrefUtil.FOLLOW_SYSTEM
import com.blockchain.blocktrack.utils.ThemePrefUtil.KEY_NIGHT_MODE
import com.blockchain.blocktrack.utils.ThemePrefUtil.LIGHT
import com.blockchain.blocktrack.utils.pref
import com.blockchain.blocktrack.utils.setDefaultNightMode
import com.blockchain.blocktrack.utils.supportsDynamicTheming

fun ColorScheme.toAmoled(): ColorScheme {
    fun Color.darken(fraction: Float = 0.5f): Color = blend(Color.Black, fraction)
    return copy(
        primary = primary.darken(0.3f),
        onPrimary = onPrimary.darken(0.3f),
        primaryContainer = primaryContainer.darken(0.3f),
        onPrimaryContainer = onPrimaryContainer.darken(0.3f),
        inversePrimary = inversePrimary.darken(0.3f),
        secondary = secondary.darken(0.3f),
        onSecondary = onSecondary.darken(0.3f),
        secondaryContainer = secondaryContainer.darken(0.3f),
        onSecondaryContainer = onSecondaryContainer.darken(0.3f),
        tertiary = tertiary.darken(0.3f),
        onTertiary = onTertiary.darken(0.3f),
        tertiaryContainer = tertiaryContainer.darken(0.3f),
        onTertiaryContainer = onTertiaryContainer.darken(0.2f),
        background = Color.Black,
        onBackground = onBackground.darken(0.15f),
        surface = Color.Black,
        onSurface = onSurface.darken(0.15f),
        surfaceVariant = surfaceVariant,
        onSurfaceVariant = onSurfaceVariant,
        surfaceTint = surfaceTint,
        surfaceContainer = surfaceContainer.darken(0.4f),
        inverseSurface = inverseSurface.darken(),
        inverseOnSurface = inverseOnSurface.darken(0.2f),
        outline = outline.darken(0.2f),
        outlineVariant = outlineVariant.darken(0.2f)
    )
}

private val lightScheme = lightColorScheme(
    primary = Purple40,
    onPrimary = Color.White,
    primaryContainer = Purple90,
    onPrimaryContainer = Purple10,
    secondary = Orange40,
    onSecondary = Color.White,
    secondaryContainer = Orange90,
    onSecondaryContainer = Orange10,
    tertiary = Blue40,
    onTertiary = Color.White,
    tertiaryContainer = Blue90,
    onTertiaryContainer = Blue10,
    error = Red40,
    onError = Color.White,
    errorContainer = Red90,
    onErrorContainer = Red10,
    background = DarkPurpleGray99,
    onBackground = DarkPurpleGray10,
    surface = DarkPurpleGray99,
    onSurface = DarkPurpleGray10,
    surfaceVariant = PurpleGray90,
    onSurfaceVariant = PurpleGray30,
    inverseSurface = DarkPurpleGray20,
    inverseOnSurface = DarkPurpleGray95,
    outline = PurpleGray50,
)

private val darkScheme = darkColorScheme(
    primary = Purple80,
    onPrimary = Purple20,
    primaryContainer = Purple30,
    onPrimaryContainer = Purple90,
    secondary = Orange80,
    onSecondary = Orange20,
    secondaryContainer = Orange30,
    onSecondaryContainer = Orange90,
    tertiary = Blue80,
    onTertiary = Blue20,
    tertiaryContainer = Blue30,
    onTertiaryContainer = Blue90,
    error = Red80,
    onError = Red20,
    errorContainer = Red30,
    onErrorContainer = Red90,
    background = DarkPurpleGray10,
    onBackground = DarkPurpleGray90,
    surface = DarkPurpleGray10,
    onSurface = DarkPurpleGray90,
    surfaceVariant = PurpleGray30,
    onSurfaceVariant = PurpleGray80,
    inverseSurface = DarkPurpleGray90,
    inverseOnSurface = DarkPurpleGray10,
    outline = PurpleGray60,
)
val LocalTheme = compositionLocalOf<MutableIntState> { error("no LocalTheme") }

@Composable
fun BlockTrackTheme(
    disableDynamicTheming: Boolean = true,
    content: @Composable () -> Unit,
) {
    val context = LocalKmpContext.current
    val theme = mutableIntStateOf(context.pref.getInt(KEY_NIGHT_MODE, FOLLOW_SYSTEM))

    LaunchedEffect(theme.value) {
        context.setDefaultNightMode(theme.value)
    }

    CompositionLocalProvider(
        LocalTheme provides theme
    ) {
        var nightModeValue = theme.value
        if (nightModeValue == FOLLOW_SYSTEM) {
            nightModeValue = if (isSystemInDarkTheme()) DARK else LIGHT
        }
        val colorScheme = remember(
            nightModeValue, disableDynamicTheming,
            lightScheme,
            darkScheme
        ) {
            context.generateColorScheme(
                nightModeValue, disableDynamicTheming,
                lightScheme,
                darkScheme
            )
        }
        MaterialTheme(
            colorScheme = colorScheme,
            typography = BlockTrackTypography,
            content = {
                Surface(content = content)
            }
        )
    }
}

expect fun KmpContext.generateColorScheme(
    nightModeValue: Int,
    dynamicColor: Boolean,
    lightScheme: ColorScheme,
    darkScheme: ColorScheme
): ColorScheme