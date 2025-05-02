plugins {
    id("app.library.kotlin.multiplatform")
    id("app.koin.compose.multiplatform")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(projects.shared.core.utilsLoading)
            api(projects.shared.functionality.exploreContent.entities)
            implementation(projects.shared.functionality.exploreContent.abstractions)
        }
    }
}

android {
    namespace = "de.tuhh.quizi.functionality.explore.content.usecases"
}