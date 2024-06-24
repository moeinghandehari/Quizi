package plugins

import org.gradle.api.Plugin
import org.gradle.api.Project
import plugins.config.configureAndroidApplication
import plugins.config.configureKotlinMultiplatform

class KotlinMultiplatformApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.multiplatform")
            }

            configureKotlinMultiplatform()
            configureAndroidApplication()
        }
    }
}