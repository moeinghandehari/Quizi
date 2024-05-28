package plugins

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import plugins.extensions.catalog
import plugins.extensions.sourceSets

class KoinCoreConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("org.jetbrains.kotlin.multiplatform")
            }
            extensions.configure<KotlinMultiplatformExtension> {
                sourceSets {
                    commonMain.dependencies {
                        implementation(project.dependencies.platform(catalog.findLibrary("koin.bom").get()))
                        implementation(catalog.findLibrary("koin.core").get())
                    }
                }
            }
        }
    }
}
