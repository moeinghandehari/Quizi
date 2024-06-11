import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    id("app.application.kotlin.multiplatform")
    id("app.firebase.application")
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlinxSerialization)
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            implementation(libs.compose.ui.tooling.preview)
            implementation(libs.androidx.ui.tooling.preview.android)
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.firebase.analytics)
            implementation(libs.firebase.crashlytics)
        }

        iosMain.dependencies {}

        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
        }

        wasmJsMain.dependencies {}

        commonMain.dependencies {
            implementation(project.dependencies.platform(libs.compose.bom))
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.uiUtil)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)

            implementation(project.dependencies.platform(libs.koin.bom))
            api(libs.koin.core)

            implementation(project.dependencies.platform(libs.firebase.bom))

            implementation(project(":shared:functionality:project-defaults"))

            // api(projects.shared.core.model)

            // Temp Workaround for a bug - Should be removed later
            implementation("co.touchlab:stately-common:2.0.6")
        }

        // Temp Workaround for a bug - Should be removed later
        configurations.all {
            exclude(group = "co.touchlab", module = "stately-strict-jvm")
        }
    }
}

android {
    namespace = "de.tuhh.quizi"
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "de.tuhh.quizi"
            packageVersion = "1.0.0"
        }
    }
}