plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    alias(libs.plugins.kotlinxSerialization)
    alias(libs.plugins.ksp)
    application
}

group = "de.tuhh.quizi.server"
version = "1.0.0"

application {
    mainClass.set("de.tuhh.quizi.server.ApplicationKt")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=${extra["io.ktor.development"] ?: "false"}")
}

dependencies {
    implementation(libs.logback)

    // Ktor
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)
    implementation(libs.ktor.server.content.negotiation)
    implementation(libs.ktor.server.host.common)
    implementation(libs.ktor.server.call.logging)
    implementation(libs.ktor.serialization.json)

    // Koin
    implementation(project.dependencies.platform(libs.koin.bom))
    implementation(libs.koin.ktor)
    implementation(libs.koin.logger)

    // Database
    implementation(libs.postgres)

    // Exposed
    implementation(libs.exposed.core)
    implementation(libs.exposed.dao)
    implementation(libs.exposed.jdbc)

    // Testing
    testImplementation(libs.ktor.server.tests)
    testImplementation(libs.kotlin.test.junit)
}
