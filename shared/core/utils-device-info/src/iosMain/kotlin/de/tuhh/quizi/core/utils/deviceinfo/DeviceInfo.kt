package de.tuhh.quizi.core.utils.deviceinfo

import androidx.compose.ui.text.intl.Locale

// Please only add functions which can be used more than one place, like a common source.
// DO NOT add a method for feature specific.
// Functionality specific methods should be placed corresponded functionality module.

actual fun getPlatformName() = "iOS"
actual fun getPreferredLanguageCode() = Locale.current.language
