package plugins.config

import io.github.gmazzo.android.test.aggregation.TestAggregationExtension
import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.tasks.util.PatternFilterable
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType
import org.gradle.testing.jacoco.plugins.JacocoPluginExtension

private val coverageExclusions = listOf(
    "**/*JsonAdapter.*",
    "**/*Module*",
    "**/di/**",
)

private val coverageInclusions = listOf(
    "**/functionality/**",
)

internal fun Project.configureJacocoKotlin() {
    with(pluginManager) {
        apply("org.gradle.jacoco")
    }

    val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
    configure<JacocoPluginExtension> {
        toolVersion = libs.findVersion("jacoco").get().toString()
    }
}

/**
 * Configure coverage aggregation throughout the project.
 * The used plugin will configure android modules in the project depending on their flavors as well.
 * However to avoid duplication only one variant can be used in the merged report. The plugin will internally depend on
 * the last configured flavor. To make it more predictive and to avoid implicit decisions which flavor is used,
 * coverage aggregation is explicitly enabled/disabled while configuring the flavors (see [configureFlavors]).
 */
internal fun Project.configureJacocoProject() {
    // Configure since aggregation also relies on jacoco and a version needs to be specified
    configureJacocoKotlin()

    with(pluginManager) {
        apply("io.github.gmazzo.test.aggregation.coverage")
    }

    configure<TestAggregationExtension> {
        coverage {
            include(coverageInclusions)
            exclude(coverageExclusions)
        }
    }
}

private fun TestAggregationExtension.coverage(configure: Action<PatternFilterable>): Unit =
    (this as org.gradle.api.plugins.ExtensionAware).extensions.configure("coverage", configure)
