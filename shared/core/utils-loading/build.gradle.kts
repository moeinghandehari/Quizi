plugins {
    id("app.library.kotlin.multiplatform")
    alias(libs.plugins.kotlinxSerialization)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(libs.kotlinx.serialization.json)
        }
    }
}

android {
    namespace = "de.tuhh.quizi.core.utils.loading"
}