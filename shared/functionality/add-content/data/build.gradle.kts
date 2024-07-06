plugins {
    id("app.library.kotlin.multiplatform")
    id("app.koin.compose.multiplatform")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.shared.core.utilsLoading)
            implementation(projects.shared.functionality.addContent.abstractions)
        }
    }
}

android {
    namespace = "de.tuhh.quizi.functionality.add.content.data"
}
