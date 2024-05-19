package de.tuhh.quizi.server

import de.tuhh.quizi.server.data.db.DatabaseSingleton
import de.tuhh.quizi.server.data.di.dataModule
import de.tuhh.quizi.server.di.appModule
import de.tuhh.quizi.server.plugins.configureMonitoring
import de.tuhh.quizi.server.plugins.configureRouting
import de.tuhh.quizi.server.plugins.configureSerialization
import io.ktor.server.application.*
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.*
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun main(args: Array<String>){
    embeddedServer(Netty, port = 8080){
        main()
        module()
    }.start(wait = true)

}

fun Application.module() {
    DatabaseSingleton.init()
    configureSerialization()
    configureMonitoring()
    configureRouting()
}

fun Application.main() {
    install(Koin) {
        slf4jLogger()
        modules(appModule, dataModule)
    }
}