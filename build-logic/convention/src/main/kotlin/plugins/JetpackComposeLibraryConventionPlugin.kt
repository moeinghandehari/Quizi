package plugins

import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import plugins.config.configureFlavors
import plugins.config.configureJetpackCompose

class JetpackComposeLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("com.android.library")
                apply("com.google.devtools.ksp")
                apply("org.jetbrains.compose")
                apply("org.jetbrains.kotlin.plugin.compose")
            }

            val extension = extensions.getByType<LibraryExtension>()
            configureJetpackCompose()

            // Can be removed if flavors are not needed for e.g. brand specific resources in the UI
            configureFlavors(extension)
        }
    }
}
