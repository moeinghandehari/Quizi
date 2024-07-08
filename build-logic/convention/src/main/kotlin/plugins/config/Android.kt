package plugins.config

import AppBuildConfig
import com.android.build.api.dsl.ApplicationBaseFlavor
import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.variant.AndroidComponentsExtension
import com.android.build.api.variant.ApplicationAndroidComponentsExtension
import com.android.build.api.variant.LibraryAndroidComponentsExtension
import com.android.build.gradle.LibraryExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.get
import org.gradle.kotlin.dsl.getByType

private fun AndroidComponentsExtension<*, *, *>.configureCommonAndroid(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply {
        compileSdk = AppBuildConfig.targetAndCompileSdk

        defaultConfig {
            minSdk = AppBuildConfig.minSdk
            vectorDrawables.useSupportLibrary = true
        }
    }

    // Add kotlin source directory to default java source directory for Sonarcloud
    commonExtension.sourceSets["main"].java.srcDir("src/main/kotlin")
    onVariants { variant ->
        commonExtension.sourceSets[variant.name].java.srcDir("src/${variant.name}/kotlin")
    }
}

internal fun Project.configureAndroidLibrary() {
    extensions.configure<LibraryExtension> {
        defaultConfig {
            // Propagated library module specific rules to any consumer
            consumerProguardFiles("proguard-rules.pro")
        }

        val androidComponentsExtension = extensions.getByType<LibraryAndroidComponentsExtension>()
        androidComponentsExtension.configureCommonAndroid(commonExtension = this)
    }
}

internal fun Project.configureAndroidApplication() {
    extensions.configure<ApplicationExtension> {
        // configureFlavors(this)

        buildFeatures {
            buildConfig = true
        }

        defaultConfig {
            targetSdk = AppBuildConfig.targetAndCompileSdk
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }

        val androidComponentsExtension =
            extensions.getByType<ApplicationAndroidComponentsExtension>()
        androidComponentsExtension.configureCommonAndroid(commonExtension = this)

        // Define name for app builds
        androidComponentsExtension.updateBuildFileNames(
            this@configureAndroidApplication,
            defaultConfig
        )
    }
}

private fun ApplicationAndroidComponentsExtension.updateBuildFileNames(
    target: Project,
    defaultConfig: ApplicationBaseFlavor,
) {
    onVariants {
        val projectName = target.rootProject.name
            .lowercase()
            .replace(' ', '-')
        target.setProperty(
            "archivesBaseName",
            "$projectName-v${defaultConfig.versionName}-${defaultConfig.versionCode}",
        )
    }
}