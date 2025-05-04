plugins {
    id("app.library.kotlin.multiplatform")
    id("app.koin.compose.multiplatform")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.shared.functionality.exploreContent.abstractions)
            implementation(projects.shared.functionality.exploreContent.data.exploreContentQuiziApi)
        }
    }
}

android {
    namespace = "de.tuhh.quizi.functionality.explore.content.data.implementations"
}

tasks.named("wasmJsBrowserProductionWebpack").configure {
    enabled = false
}