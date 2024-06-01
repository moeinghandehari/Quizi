plugins {
    id("app.library.kotlin.multiplatform")
}

kotlin {
    sourceSets {
        commonMain.dependencies {}
    }
}

android {
    namespace = "de.tuhh.quizi.feature.quizinput.abstractions"
}
