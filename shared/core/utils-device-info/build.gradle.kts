plugins {
    id("app.library.kotlin.multiplatform")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.jetbrains.compose.ui)
        }
    }
}

android {
    namespace = "de.tuhh.quizi.core.utils.deviceinfo"
}