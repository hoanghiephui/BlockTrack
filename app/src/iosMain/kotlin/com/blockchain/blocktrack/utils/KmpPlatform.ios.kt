package com.blockchain.blocktrack.utils

import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.Settings
import kotlinx.cinterop.ExperimentalForeignApi
import okio.Path.Companion.toPath
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDefaults
import platform.Foundation.NSUserDomainMask

actual fun supportsDynamicTheming(): Boolean {
    return false //TODO("Not yet implemented")
}

actual abstract class KmpContext

actual val KmpContext.pref: Settings
    get() = NSUserDefaultsSettings(NSUserDefaults())

actual fun KmpContext.setDefaultNightMode(mode: Int) {
}

actual val KmpContext.dataStoreDir get() = appDocDir().resolve("dataStore")

@OptIn(ExperimentalForeignApi::class)
private fun appDocDir() = NSFileManager.defaultManager.URLForDirectory(
    directory = NSDocumentDirectory,
    inDomain = NSUserDomainMask,
    appropriateForURL = null,
    create = false,
    error = null,
)!!.path!!.toPath()