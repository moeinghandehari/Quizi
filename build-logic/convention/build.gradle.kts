import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "de.tuhh.quizi.buildlogic"

// Configure the build-logic plugins to target JDK 17
// This matches the JDK used to build the project, and is not related to what is running on device.
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    compileOnly(libs.android.gradle)
    compileOnly(libs.kotlin.gradle)
    compileOnly(libs.ksp.gradle)
    compileOnly(libs.detekt.gradle)
    compileOnly(libs.coverageAggregation.gradle)
    compileOnly(libs.firebase.crashlytics.gradle)
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {
        // Android
        register("androidLibrary") {
            id = "app.android.library"
            implementationClass = "plugins.AndroidLibraryConventionPlugin"
        }

        // Compose
        register("composeApplication") {
            id = "app.compose.application"
            implementationClass = "plugins.JetpackComposeApplicationConventionPlugin"
        }
        register("composeLibrary") {
            id = "app.compose.library"
            implementationClass = "plugins.JetpackComposeLibraryConventionPlugin"
        }

        // Kotlin
        register("kotlinMultiplatformApplication") {
            id = "app.application.kotlin.multiplatform"
            implementationClass = "plugins.KotlinMultiplatformApplicationConventionPlugin"
        }
        register("kotlinMultiplatformLibrary") {
            id = "app.library.kotlin.multiplatform"
            implementationClass = "plugins.KotlinMultiplatformLibraryConventionPlugin"
        }

        // Koin
        register("koinCompose") {
            id = "app.koin.compose.multiplatform"
            implementationClass = "plugins.KoinComposeConventionPlugin"
        }

        // Firebase
        register("firebase") {
            id = "app.firebase.application"
            implementationClass = "plugins.FirebaseApplicationConventionPlugin"
        }

        // Project
        register("project") {
            id = "app.project"
            implementationClass = "plugins.ProjectConventionPlugin"
        }
    }
}