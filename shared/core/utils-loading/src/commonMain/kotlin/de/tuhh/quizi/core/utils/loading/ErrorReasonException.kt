package de.tuhh.quizi.core.utils.loading

data class ErrorReasonException(
    val reason: ErrorReason,
) : Exception()
