package com.blockchain.blocktrack.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import coil3.ImageLoader
import coil3.disk.DiskCache
import coil3.memory.MemoryCache
import coil3.request.CachePolicy
import com.blockchain.blocktrack.utils.KmpContext
import com.blockchain.blocktrack.utils.coilContext
import com.blockchain.blocktrack.utils.dataStoreDir
import com.blockchain.blocktrack.utils.imageCacheDir
import kotlinx.serialization.json.Json
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.KmpComponentCreate
import me.tatarka.inject.annotations.Provides
import me.tatarka.inject.annotations.Scope

@Scope
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER)
annotation class AppSingleton

@AppSingleton
@Component
abstract class AppComponent(
    @get:Provides val context: KmpContext
) {
    @get:Provides
    @get:AppSingleton
    val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
        explicitNulls = false
    }

    @Provides
    @AppSingleton
    fun providePreferences(context: KmpContext): DataStore<Preferences> =
        PreferenceDataStoreFactory.createWithPath(
            corruptionHandler = null,
            migrations = emptyList(),
            produceFile = { context.dataStoreDir.resolve("settings.preferences_pb") },
        )

    @Provides
    @AppSingleton
    fun provideImageLoader(): ImageLoader =
        ImageLoader.Builder(context.coilContext)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .memoryCache(
                MemoryCache.Builder()
                    .maxSizePercent(context.coilContext, 0.2)
                    .build()
            )
            .diskCachePolicy(CachePolicy.ENABLED)
            .diskCache(
                DiskCache.Builder()
                    .maxSizeBytes(50L * 1024L * 1024L)
                    .directory(context.imageCacheDir)
                    .build()
            )
            .build()

    companion object
}


@KmpComponentCreate
expect fun AppComponent.Companion.create(context: KmpContext): AppComponent