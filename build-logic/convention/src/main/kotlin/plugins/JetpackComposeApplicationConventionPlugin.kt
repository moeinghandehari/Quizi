package plugins

import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import plugins.config.configureFlavors
import plugins.config.configureJetpackCompose

class JetpackComposeApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("com.android.application")
                apply("com.google.devtools.ksp")
                apply("org.jetbrains.compose")
                apply("org.jetbrains.kotlin.plugin.compose")
            }

            configureJetpackCompose()

            extensions.configure<ApplicationExtension> {
                configureFlavors(this)
            }
        }
    }
}