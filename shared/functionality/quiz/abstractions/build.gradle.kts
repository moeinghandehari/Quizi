plugins {
    id("app.library.kotlin.multiplatform")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(projects.shared.core.utilsLoading)
            api(projects.shared.functionality.quiz.entities)
        }
    }
}

android { namespace = "de.tuhh.quizi.functionality.quiz.abstractions" }