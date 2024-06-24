import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    id("app.application.kotlin.multiplatform")
    id("app.compose.application")
    id("app.firebase.application")
    alias(libs.plugins.kotlinxSerialization)
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.jetbrains.compose.ui.tooling.preview)
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
            implementation(project.dependencies.platform(libs.koin.bom))
            api(libs.koin.core)

            implementation(project.dependencies.platform(libs.firebase.bom))

            implementation(compose.components.resources)

            implementation(projects.ui.core)
            implementation(projects.ui.addContent)
            implementation(projects.ui.quiz)
            implementation(projects.shared.functionality.projectDefaults)
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