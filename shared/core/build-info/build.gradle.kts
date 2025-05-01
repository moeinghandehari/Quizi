plugins {
    id("app.library.kotlin.multiplatform")
}

kotlin {
    sourceSets {
        // Workaround for https://youtrack.jetbrains.com/issue/KT-66159/
        // Also copy the generated skiko.mjs to the packages which cause error
        wasmJsMain.dependencies {
            implementation(npm("aaa-kilua-assets", "0.0.7"))
        }
    }
}

android {
    namespace = "de.tuhh.quizi.core.build.info"
}