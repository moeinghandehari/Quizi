plugins {
    id("app.library.kotlin.multiplatform")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            // put your Multiplatform dependencies here
            implementation(libs.kotlinx.serialization)
            implementation(project.dependencies.platform(libs.koin.bom))
            api(libs.koin.core)
            implementation(project.dependencies.platform(libs.ktor.bom))
            // implementation(libs.ktor.core)
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
            // implementation(libs.ktor.client.js)
        }
    }
}

android {
    namespace = "de.tuhh.quizi.core.utils.network"
}
