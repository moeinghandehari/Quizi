plugins {
    id("app.library.kotlin.multiplatform")
    id("app.koin.compose.multiplatform")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(projects.shared.core.utilsLoading)
            api(projects.shared.functionality.quiz.entities)
            implementation(projects.shared.functionality.quiz.abstractions)
        }
    }
}

android { namespace = "de.tuhh.quizi.functionality.quiz.usecases" }