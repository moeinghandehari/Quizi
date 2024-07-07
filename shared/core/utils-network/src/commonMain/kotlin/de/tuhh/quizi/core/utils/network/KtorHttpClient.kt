package de.tuhh.quizi.core.utils.network

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.DefaultJson
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

fun ktorHttpClient(
    isDebuggable: Boolean,
    block: HttpClientConfig<out HttpClientEngineConfig>.() -> Unit = {},
): HttpClient = HttpClient(platformHttpClientEngine()) {
    if (isDebuggable) {
        install(Logging) {
            logger = io.ktor.client.plugins.logging.Logger.DEFAULT
            level = LogLevel.BODY
        }
    }
    install(ContentNegotiation) {
        json(
            Json(DefaultJson) {
                ignoreUnknownKeys = true
            },
        )
    }

    block(this)
}