plugins {
    id("app.library.kotlin.multiplatform")
    id("app.koin.compose.multiplatform")
    alias(libs.plugins.kotlinxSerialization)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.shared.core.quiziApi)
            implementation(projects.shared.core.utilsNetwork)
            api(projects.shared.functionality.exploreContent.entities)
        }
    }
}

android {
    namespace = "de.tuhh.quizi.functionality.explore.content.data.api"
}

tasks.named("wasmJsBrowserProductionWebpack").configure {
    enabled = false
}