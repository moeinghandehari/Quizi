plugins {
    id("app.library.kotlin.multiplatform")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(projects.shared.core.utilsLoading)
            api(projects.shared.functionality.addContent.entities)
        }
    }
}

android {
    namespace = "de.tuhh.quizi.functionality.add.content.abstractions"
}
