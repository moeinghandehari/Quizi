package plugins

import org.gradle.api.Plugin
import org.gradle.api.Project
import plugins.config.configureAndroidLibrary
import plugins.config.configureKotlinAndroid

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }

            configureKotlinAndroid()
            configureAndroidLibrary()
        }
    }
}