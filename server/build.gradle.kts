plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    alias(libs.plugins.kotlinxSerialization)
    application
}

group = "de.tuhh"
version = "1.0.0"

application {
    mainClass.set("de.tuhh.ApplicationKt")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=${extra["io.ktor.development"] ?: "false"}")
}

dependencies {
    implementation(projects.shared)
    implementation(libs.logback)

    // Ktor
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)
    implementation(libs.ktor.server.content.negotiation)
    implementation(libs.ktor.server.host.common)
    implementation(libs.ktor.server.call.logging)
    implementation(libs.ktor.serialization)

    // Database
    implementation(libs.h2)

    // Exposed
    implementation(libs.exposed.core)
    implementation(libs.exposed.dao)
    implementation(libs.exposed.jdbc)

    // Testing
    testImplementation(libs.ktor.server.tests)
    testImplementation(libs.kotlin.test.junit)
}
