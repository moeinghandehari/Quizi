package de.tuhh.quizi.core.quizi.api

import de.tuhh.quizi.core.utils.deviceinfo.getPlatformName
import de.tuhh.quizi.core.utils.deviceinfo.getPreferredLanguageCode
import de.tuhh.quizi.core.utils.foundation.provideUniqueId
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.request.header

private val uniqueSessionRequestId = provideUniqueId()

private const val LANGUAGE_KEY = "Language"
private const val PLATFORM_KEY = "Platform"
private const val UNIVERSITY_KEY = "University"
private const val VERSION_KEY = "AppVersion"
private const val BUILD_KEY = "AppBuild"
private const val SESSION_REQUEST_ID = "SessionRequestId"
private const val REQUEST_ID = "RequestId"

private const val UNIVERSITY = "TUHH"

internal fun DefaultRequest.DefaultRequestBuilder.addHeaders(
    appVersionName: String,
    appVersionCode: String,
) {
    val platformName = getPlatformName()
    val selectedLanguage = getPreferredLanguageCode()
    header(LANGUAGE_KEY, selectedLanguage)
    header(PLATFORM_KEY, platformName)
    header(UNIVERSITY_KEY, UNIVERSITY)
    header(VERSION_KEY, appVersionName)
    header(BUILD_KEY, appVersionCode)
    header(SESSION_REQUEST_ID, uniqueSessionRequestId)
    header(REQUEST_ID, provideUniqueId())
}