plugins {
    id("app.library.kotlin.multiplatform")
    id("app.compose.library")
    id("app.koin.compose.multiplatform")
    alias(libs.plugins.kotlinxSerialization)
}

android { namespace = "de.tuhh.quizi.ui.addcontent" }

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.ui.core)
            implementation(projects.shared.core.utilsValidation)
            implementation(projects.shared.functionality.addContent.usecases)
            implementation(compose.components.resources)
        }
    }
}