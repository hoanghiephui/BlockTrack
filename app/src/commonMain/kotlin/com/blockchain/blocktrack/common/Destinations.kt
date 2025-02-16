package com.blockchain.blocktrack.common

import blocktrack.app.generated.resources.Res
import blocktrack.app.generated.resources.home
import blocktrack.app.generated.resources.outline_home_24
import com.blockchain.blocktrack.ui.home.HomeRoute
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import kotlin.reflect.KClass

enum class Destinations(
    val route: KClass<*>,
    val baseRoute: KClass<*> = route,
    val icon: DrawableResource = Res.drawable.outline_home_24,
    val activeIcon: DrawableResource = Res.drawable.outline_home_24,
    val label: StringResource = Res.string.home
) {
    HOME(
        route = HomeRoute::class,
        icon = Res.drawable.outline_home_24,
        activeIcon = Res.drawable.outline_home_24,
        label = Res.string.home,
        baseRoute = HomeRoute::class
    )
}