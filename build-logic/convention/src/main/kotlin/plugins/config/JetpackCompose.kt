package plugins.config

import org.gradle.api.Project
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
