package de.tuhh.quizi.core.quizi.api

import de.tuhh.quizi.core.buildinfo.BuildInfo
import de.tuhh.quizi.core.utils.network.ktorHttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.plugins.defaultRequest

internal fun ktorQuiziHttpClient(
    isDebug: Boolean,
    baseUrl: String,
    buildInfo: BuildInfo,
    block: HttpClientConfig<out HttpClientEngineConfig>.() -> Unit = {},
) = ktorHttpClient(isDebug) {
    defaultRequest {
        addDefaults(baseUrl, buildInfo)
    }
    block(this)
}