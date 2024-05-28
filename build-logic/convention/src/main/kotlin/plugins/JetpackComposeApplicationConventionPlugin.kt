package plugins

import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import plugins.config.configureJetpackCompose

class JetpackComposeApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("com.android.application")
                apply("com.google.devtools.ksp")
            }

            val applicationExtension = extensions.getByType<ApplicationExtension>()
            configureJetpackCompose(applicationExtension)
        }
    }
}