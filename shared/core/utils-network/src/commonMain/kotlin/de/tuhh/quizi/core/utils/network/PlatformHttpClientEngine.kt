package de.tuhh.quizi.core.utils.network

import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory

expect fun platformHttpClientEngine(): HttpClientEngineFactory<HttpClientEngineConfig>