plugins {
    id("app.library.kotlin.multiplatform")
    id("app.compose.library")
}

android { namespace = "de.tuhh.quizi.ui.quiz" }

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.ui.core)
        }
    }
}