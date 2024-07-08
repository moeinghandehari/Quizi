package plugins.config

import AppBuildConfig
import Identifiers
import com.android.build.api.dsl.ApplicationExtension
import extensions.param
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getValue
import org.gradle.kotlin.dsl.provideDelegate
import plugins.extensions.catalog

internal fun Project.configureJetpackCompose() {
    val implementation by configurations
    val debugImplementation by configurations

    pluginManager.apply {
        apply("com.google.devtools.ksp")
    }

    dependencies {
        implementation(platform(catalog.findLibrary("compose.bom").get()))
        implementation(catalog.findLibrary("compose.foundation").get())
        implementation(catalog.findLibrary("compose.runtime").get())
        implementation(catalog.findLibrary("compose.material3").get())
        implementation(catalog.findLibrary("compose.ui").get())
        implementation(catalog.findLibrary("compose.ui.tooling.preview").get())
        implementation(catalog.findLibrary("compose.uiUtil").get())
        // implementation(catalog.findLibrary("compose.components.resources").get())
        // implementation(catalog.findLibrary("compose.components.uiToolingPreview").get())
        debugImplementation(catalog.findLibrary("compose.ui.tooling").get())

        implementation(catalog.findLibrary("androidx.navigation.compose").get())
    }
}

internal fun Project.configureComposeApplication() {
    extensions.configure<ApplicationExtension> {
        buildFeatures {
            buildConfig = true
        }

        defaultConfig {
            targetSdk = AppBuildConfig.targetAndCompileSdk
            versionCode = 1
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }

        signingConfigs {
            /*getByName(Identifiers.SigningConfigs.DEBUG) {
                storeFile = rootProject.file("config/signing/debug.keystore")
            }*/
            create(Identifiers.SigningConfigs.RELEASE) {
                storeFile =
                    file(
                        param(Identifiers.Params.SIGNING_KEYSTORE_PATH)?.takeUnless { it.isEmpty() }
                            ?: "notSet"
                    )
                storePassword =
                    param(Identifiers.Params.SIGNING_KEYSTORE_PASSWORD)?.takeUnless { it.isEmpty() }
                        ?: "notSet"
                keyAlias = param(Identifiers.Params.SIGNING_KEY_ALIAS)?.takeUnless { it.isEmpty() }
                    ?: "notSet"
                keyPassword =
                    param(Identifiers.Params.SIGNING_KEY_PASSWORD)?.takeUnless { it.isEmpty() }
                        ?: "notSet"
            }
        }

        buildTypes {
            getByName(Identifiers.BuildTypes.DEBUG) {
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
            }
            getByName(Identifiers.BuildTypes.RELEASE) {
                signingConfig = signingConfigs.getByName(Identifiers.SigningConfigs.RELEASE)
                isMinifyEnabled = param(Identifiers.Params.IS_MINIFY_ENABLED)?.toBoolean() ?: true
                isDebuggable = false

                buildConfigField(
                    "String",
                    "QUIZI_API_BASE_URL",
                    "\"${AppBuildConfig.quiziApiBuildConfig.baseUrlDev}\"",
                )
            }
        }
    }
}

/**
 * Gets a camel case name of the project depending on its parent projects.
 *
 * E.g. root/ui/apartment-search-area/start will return UiApartmentSearchAreaStart
 *
 * @param takeUntil Optional parameter to specify at which parent module to stop
 */
/*private fun Project.moduleName(takeUntil: (Project) -> Boolean = { false }) = buildList {
    var currentProject: Project? = project
    while (currentProject != null) {
        add(currentProject.name)
        currentProject = currentProject.parent?.takeUnless {
            // Stop if the root project is reached or if it should break early
            it == project.rootProject || takeUntil(it)
        }
    }
}
    .reversed()
    .map { it.split("[^A-Za-z]".toRegex()) }
    .flatten()
    .joinToString(separator = "") { it.capitalized() }*/
