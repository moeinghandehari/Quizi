plugins {
    id("app.library.kotlin.multiplatform")
    alias(libs.plugins.kotlinxSerialization)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.serialization)
            implementation(project.dependencies.platform(libs.koin.bom))
            api(libs.koin.core)
        }
    }
}

android {
    namespace = "de.tuhh.quizi.feature.projectdefaults"
}
