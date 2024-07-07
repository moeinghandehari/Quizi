plugins {
    id("app.library.kotlin.multiplatform")
    id("app.koin.compose.multiplatform")
    alias(libs.plugins.kotlinxSerialization)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.shared.core.buildInfo)
            implementation(projects.shared.core.utilsFoundation)
            implementation(projects.shared.core.utilsDeviceInfo)
            api(projects.shared.core.utilsNetwork)
        }
    }
}

android {
    namespace = "de.tuhh.quizi.shared.core.quizi.api"
}
