import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    id("app.application.kotlin.multiplatform")
    id("app.compose.application")
    id("app.koin.compose.multiplatform")
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
            implementation(libs.kotlin.coroutines.swing)
        }

        wasmJsMain.dependencies {}

        commonMain.dependencies {
            implementation(projects.shared.core.buildInfo) {
                because("The app provides the actual implementation of the build info")
            }

            implementation(project.dependencies.platform(libs.firebase.bom))

            implementation(compose.components.resources)

            implementation(projects.ui.core)
            implementation(projects.ui.addContent)
            implementation(projects.ui.home)
            implementation(projects.ui.quiz)

            implementation(projects.shared.functionality.addContent.data)
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