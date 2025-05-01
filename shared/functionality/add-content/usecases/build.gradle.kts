plugins {
    id("app.library.kotlin.multiplatform")
    id("app.koin.compose.multiplatform")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(projects.shared.core.utilsLoading)
            api(projects.shared.functionality.addContent.entities)
            implementation(projects.shared.functionality.addContent.abstractions)
        }
    }
}

android {
    namespace = "de.tuhh.quizi.functionality.add.content.usecases"
}