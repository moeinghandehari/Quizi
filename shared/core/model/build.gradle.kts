plugins {
    id("app.library.kotlin.multiplatform")
    alias(libs.plugins.kotlinxSerialization)
}

android {
    namespace = "de.tuhh.quizi.shared.core.model"
}
