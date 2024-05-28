package plugins.config

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.configurationcache.extensions.capitalized
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.getValue
import org.gradle.kotlin.dsl.provideDelegate
import plugins.extensions.catalog

internal fun Project.configureJetpackCompose(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    pluginManager.apply {
        apply("com.google.devtools.ksp")
    }

    commonExtension.apply {
        buildFeatures.compose = true
        composeOptions.kotlinCompilerExtensionVersion =
            catalog.findVersion("composeKotlinCompiler").get().toString()

        val kspExtension = extensions.getByType<com.google.devtools.ksp.gradle.KspExtension>()
        kspExtension.apply {
            // Configure Compose Destinations to generate NavGraphs containing the Destinations
            arg("compose-destinations.mode", "navgraphs")

            // Configure Compose Destinations to use the module path up to `ui` to generate names
            arg("compose-destinations.moduleName", moduleName { it.name == "ui" })
        }
    }

    val implementation by configurations
    val debugImplementation by configurations
    val ksp by configurations

    dependencies {
        ksp(catalog.findLibrary("compose.destinations.ksp").get())
        implementation(platform(catalog.findLibrary("compose.bom").get()))
        implementation(catalog.findLibrary("compose.runtime").get())
        implementation(catalog.findLibrary("compose.preview").get())
        debugImplementation(catalog.findLibrary("compose.tooling").get())
    }
}

/**
 * Gets a camel case name of the project depending on its parent projects.
 *
 * E.g. root/ui/apartment-search-area/start will return UiApartmentSearchAreaStart
 *
 * @param takeUntil Optional parameter to specify at which parent module to stop
 */
private fun Project.moduleName(takeUntil: (Project) -> Boolean = { false }) = buildList {
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
    .joinToString(separator = "") { it.capitalized() }
