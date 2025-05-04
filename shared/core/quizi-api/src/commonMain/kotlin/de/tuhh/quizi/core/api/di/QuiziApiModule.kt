package de.tuhh.quizi.core.api.di

import de.tuhh.quizi.core.buildinfo.BuildInfo
import de.tuhh.quizi.core.api.ktorQuiziHttpClient
import de.tuhh.quizi.core.api.model.QuiziApiConfig
import io.ktor.client.HttpClient
import org.koin.dsl.module

val QuiziApiModule = module {

    single<HttpClient> {
        val quiziApiConfig: QuiziApiConfig = get()
        val buildInfo: BuildInfo = get()
        ktorQuiziHttpClient(
            buildInfo.isDebuggable,
            quiziApiConfig.baseUrl,
            buildInfo,
        )
    }
}
