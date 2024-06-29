plugins {
    id("app.library.kotlin.multiplatform")
    id("app.compose.library")
    id("app.koin.compose.multiplatform")
    alias(libs.plugins.kotlinxSerialization)
}

android { namespace = "de.tuhh.quizi.ui.core" }

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.shared.core.utilsLoading)
            implementation(project.dependencies.platform(libs.compose.bom))
            api(compose.foundation)
            api(compose.material)
            api(compose.material3)
            api(compose.uiUtil)
            api(compose.components.resources)
            api(compose.components.uiToolingPreview)
            api(libs.androidx.lifecycle.runtime.compose)
            api(libs.androidx.lifecycle.viewmodel.compose)
            api(libs.androidx.navigation.compose)
            api(libs.kotlinx.serialization.json)
        }
    }
}
dependencies {
    implementation(libs.androidx.navigation.common.ktx)
}
