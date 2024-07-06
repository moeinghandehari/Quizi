package de.tuhh.quizi.ui.core.error

import de.tuhh.quizi.core.utils.loading.ErrorReason

val ErrorReason.errorDescription: String?
    get() = when (this) {
        is ErrorReason.NetworkConnection -> null // TODO to be defined in upcoming sprint

        is ErrorReason.Api -> when (this) {
            is ErrorReason.Api.Explanation -> message
            is ErrorReason.Api.Forbidden -> message
        }

        is ErrorReason.Unspecified -> message
    }