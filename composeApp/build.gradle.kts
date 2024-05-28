import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    id("app.library.kotlin.multiplatform")
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlinxSerialization)
}

kotlin {
    sourceSets {
        val desktopMain by getting

        androidMain.dependencies {
            implementation(libs.compose.ui.tooling.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.firebase.analytics)
            implementation(libs.firebase.crashlytics)
            // implementation(libs.firebase.crashlytics.gradle)
        }

        iosMain.dependencies {}

        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
        }

        wasmJsMain.dependencies {}

        commonMain.dependencies {
            implementation(project.dependencies.platform(libs.compose.bom))
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)

            implementation(project.dependencies.platform(libs.koin.bom))
            api(libs.koin.core)

            implementation(project.dependencies.platform(libs.firebase.bom))

            implementation(project(":feature:project-defaults"))

            // api(projects.shared.core.model)

            // Temp Workaround for a bug - Should be removed later
            implementation("co.touchlab:stately-common:2.0.6")
        }

        // Temp Workaround for a bug - Should be removed later
        configurations.all {
            exclude(group = "co.touchlab", module = "stately-strict-jvm")
        }
    }
}

android {
    namespace = "de.tuhh.quizi.composeApp"

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    AppBuildConfig.BrandFlavorDimension.productFlavors.forEach { brandFlavor ->
        val brandName = brandFlavor.name
        val taskName = brandName.replaceFirstChar { it.uppercaseChar() }

        tasks.register("getStrings$taskName") {
            group = "quizi"

            finalizedBy("getStrings")
        }
    }
//
//    productFlavors {
//        AppBuildConfig.BrandFlavorDimension.productFlavors.forEach { flavor ->
//            getByName(flavor.name) {
//                applicationId = flavor.applicationId
//            }
//        }
//    }
    android.buildFeatures.buildConfig = true

    signingConfigs {
        /*getByName(Identifiers.SigningConfigs.DEBUG) {
            storeFile = rootProject.file("config/signing/debug.keystore")
        }*/
        /*create(Identifiers.SigningConfigs.RELEASE) {
            storeFile = file(
                param(
                    Identifiers.Params.SIGNING_KEYSTORE_PATH,
                )?.takeUnless {
                    it.isEmpty()
                } ?: "notSet",
            )
            storePassword =
                param(Identifiers.Params.SIGNING_KEYSTORE_PASSWORD)?.takeUnless { it.isEmpty() }
                    ?: "notSet"
            keyAlias =
                param(Identifiers.Params.SIGNING_KEY_ALIAS)?.takeUnless { it.isEmpty() } ?: "notSet"
            keyPassword =
                param(Identifiers.Params.SIGNING_KEY_PASSWORD)?.takeUnless { it.isEmpty() }
                    ?: "notSet"
        }*/
    }

    buildTypes {
        /*getByName(Identifiers.BuildTypes.DEBUG) {
            signingConfig = signingConfigs.getByName(Identifiers.SigningConfigs.DEBUG)
            applicationIdSuffix = ".debug"
            // For debuggable builds, minification is limited by AGP and will not obfuscate names
            isMinifyEnabled = param(Identifiers.Params.IS_MINIFY_ENABLED)?.toBoolean() ?: false
            isDebuggable = true

            buildConfigField(
                "String",
                "QUIZI_API_BASE_URL",
                "\"${AppBuildConfig.quiziApiBuildConfig.baseUrlDev}\"",
            )
        }*/
        /*getByName(Identifiers.BuildTypes.RELEASE) {
            signingConfig = signingConfigs.getByName(Identifiers.SigningConfigs.RELEASE)
            isMinifyEnabled = param(Identifiers.Params.IS_MINIFY_ENABLED)?.toBoolean() ?: true
            isDebuggable = false

            buildConfigField(
                "String",
                "QUIZI_API_BASE_URL",
                "\"${AppBuildConfig.quiziApiBuildConfig.baseUrlDev}\"",
            )
        }*/
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    dependencies {
        debugImplementation(libs.compose.ui.tooling)
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "de.tuhh.quizi"
            packageVersion = "1.0.0"
        }
    }
}