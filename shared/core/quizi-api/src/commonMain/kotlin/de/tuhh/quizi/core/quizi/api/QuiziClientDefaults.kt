package de.tuhh.quizi.core.quizi.api

import de.tuhh.quizi.core.buildinfo.BuildInfo
import io.ktor.client.plugins.DefaultRequest
import io.ktor.http.ContentType
import io.ktor.http.contentType

internal fun DefaultRequest.DefaultRequestBuilder.addDefaults(
    baseUrl: String,
    buildInfo: BuildInfo,
) {
    contentType(ContentType.Application.Json)
    url(scheme = "http", host = baseUrl, port = 8080)
    addHeaders(
        buildInfo.appVersionName,
        buildInfo.appVersionCode,
    )
}