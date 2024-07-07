plugins {
    id("app.library.kotlin.multiplatform")
    id("app.koin.compose.multiplatform")
    alias(libs.plugins.kotlinxSerialization)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            // put your Multiplatform dependencies here
            api(project.dependencies.platform(libs.ktor.bom))
            api(libs.bundles.ktor.multiplatform)
            api(projects.shared.core.utilsLoading)
        }

        androidMain.dependencies {
            implementation(libs.ktor.client.android)
        }

        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }

        desktopMain.dependencies {
            implementation(libs.ktor.client.java)
        }

        wasmJsMain.dependencies {
            implementation(libs.ktor.client.js)
        }
    }
}

android {
    namespace = "de.tuhh.quizi.core.utils.network"
}
