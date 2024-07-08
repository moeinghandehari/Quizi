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

        // Workaround for https://youtrack.jetbrains.com/issue/KT-66159/
        // Also copy the generated skiko.mjs to the packages which cause error
        wasmJsMain.dependencies {
            implementation(npm("aaa-kilua-assets", "0.0.7"))
        }
    }
}

android {
    namespace = "de.tuhh.quizi.shared.core.quizi.api"
}
