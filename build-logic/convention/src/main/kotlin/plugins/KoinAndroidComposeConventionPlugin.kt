package plugins

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getValue
import org.gradle.kotlin.dsl.provideDelegate
import plugins.extensions.catalog

class KoinAndroidComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            val implementation by configurations

            dependencies {
                implementation(platform(catalog.findLibrary("koin.bom").get()))
                implementation(catalog.findLibrary("koin.android.compose").get())
            }
        }
    }
}
