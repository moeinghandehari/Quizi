package de.tuhh.quizi.core.utils.network

import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.java.Java

actual fun platformHttpClientEngine(): HttpClientEngineFactory<HttpClientEngineConfig> = Java