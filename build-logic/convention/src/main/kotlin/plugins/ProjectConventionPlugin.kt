package plugins

import org.gradle.api.Plugin
import org.gradle.api.Project
import plugins.config.configureDetektProject
// import plugins.config.configureJacocoProject

class ProjectConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            // configureJacocoProject()
            configureDetektProject()
        }
    }
}