package de.tuhh.quizi.server.exceptionHandler

sealed class DbExceptionHandler(override val message: String? = null, private val throwable: Throwable? = null) : Throwable(message, throwable) {
    data class InsertionException(override val message: String? = null, val throwable: Throwable? = null) : DbExceptionHandler(message, throwable)
}