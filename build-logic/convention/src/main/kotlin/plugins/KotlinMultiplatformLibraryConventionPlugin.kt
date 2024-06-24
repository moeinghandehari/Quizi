package plugins

import org.gradle.api.Plugin
import org.gradle.api.Project
import plugins.config.configureAndroidLibrary
import plugins.config.configureKotlinMultiplatform

class KotlinMultiplatformLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("org.jetbrains.kotlin.multiplatform")
                apply("com.android.library")
            }

            configureKotlinMultiplatform()
            configureAndroidLibrary()
        }
    }
}
