package de.tuhh.quizi.server

import de.tuhh.quizi.server.data.db.DatabaseSingleton
import de.tuhh.quizi.server.plugins.configureMonitoring
import de.tuhh.quizi.server.plugins.configureRouting
import de.tuhh.quizi.server.plugins.configureSerialization
import io.ktor.server.application.*
import io.ktor.server.netty.*

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module() {
    DatabaseSingleton.init()
    configureSerialization()
    configureMonitoring()
    configureRouting()
}
