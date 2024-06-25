package de.tuhh.quizi.core.utils.loading

import kotlinx.serialization.Serializable

@Serializable
sealed class ErrorReason {

    @Serializable
    data object NetworkConnection : ErrorReason()

    sealed class Api : ErrorReason() {
        @Serializable
        data class Explanation(val message: String) : Api()

        @Serializable
        data class Forbidden(val message: String?) : Api()
    }

    @Serializable
    data class Unspecified(val message: String?) : ErrorReason()
}
