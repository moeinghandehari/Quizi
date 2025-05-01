plugins {
    id("app.library.kotlin.multiplatform")
    id("app.compose.library")
    id("app.koin.compose.multiplatform")
    alias(libs.plugins.kotlinxSerialization)
}

android { namespace = "de.tuhh.quizi.ui.quiz" }

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.ui.core)
            implementation(projects.shared.core.utilsLoading)
        }
    }
}