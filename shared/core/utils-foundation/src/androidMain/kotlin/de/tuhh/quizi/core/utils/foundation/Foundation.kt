package de.tuhh.quizi.core.utils.foundation

import java.util.UUID

// Please only add functions which can be used more than one place, like a common source.
// DO NOT add a method for feature specific.
// Functionality specific methods should be placed corresponded functionality module.

actual fun provideUniqueId() = UUID.randomUUID().toString()