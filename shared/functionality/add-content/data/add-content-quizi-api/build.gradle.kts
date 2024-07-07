plugins {
    id("app.library.kotlin.multiplatform")
    id("app.koin.compose.multiplatform")
    alias(libs.plugins.kotlinxSerialization)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.shared.core.quiziApi)
            implementation(projects.shared.core.utilsNetwork)
            api(projects.shared.functionality.addContent.entities)
        }
    }
}

android {
    namespace = "de.tuhh.quizi.functionality.add.content.data.api"
}
