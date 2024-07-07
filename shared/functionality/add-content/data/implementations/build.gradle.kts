plugins {
    id("app.library.kotlin.multiplatform")
    id("app.koin.compose.multiplatform")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.shared.functionality.addContent.abstractions)
            implementation(projects.shared.functionality.addContent.data.addContentQuiziApi)
        }
    }
}

android {
    namespace = "de.tuhh.quizi.functionality.add.content.data.implementations"
}
