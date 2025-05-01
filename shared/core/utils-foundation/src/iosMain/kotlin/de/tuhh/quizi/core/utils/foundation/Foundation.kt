package de.tuhh.quizi.core.utils.foundation

import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.allocArrayOf
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.usePinned
import platform.Foundation.NSData
import platform.Foundation.NSUUID
import platform.Foundation.create
import platform.posix.memcpy

// Please only add functions which can be used more than one place, like a common source.
// DO NOT add a method for feature specific.
// Functionality specific methods should be placed corresponded functionality module.

actual fun provideUniqueId() = NSUUID().UUIDString()

@OptIn(ExperimentalForeignApi::class)
fun NSData.toByteArray(): ByteArray = ByteArray(this@toByteArray.length.toInt()).apply {
    usePinned {
        memcpy(it.addressOf(0), this@toByteArray.bytes, this@toByteArray.length)
    }
}

@OptIn(ExperimentalForeignApi::class, BetaInteropApi::class)
fun ByteArray.toNsData(): NSData = memScoped {
    NSData.create(
        bytes = allocArrayOf(this@toNsData),
        length = this@toNsData.size.toULong(),
    )
}