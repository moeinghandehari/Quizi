plugins {
    id("app.library.kotlin.multiplatform")
    id("app.koin.compose.multiplatform")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.shared.functionality.quiz.abstractions)
            implementation(projects.shared.functionality.quiz.data.api)
        }
    }
}

android { namespace = "de.tuhh.quizi.functionality.quiz.data.implementations" }

tasks.named("wasmJsBrowserProductionWebpack").configure { enabled = false }