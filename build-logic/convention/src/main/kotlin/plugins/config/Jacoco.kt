package plugins.config

import com.android.build.api.dsl.CommonExtension
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
    "**/feature/**",
)

internal fun Project.configureJacocoAndroid(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    // AGP adds a dependency to jacoco ant with an explicit version number (as specified via testCoverage.jacocoVersion)
    //  when `BuildType.enableUnitTestCoverage` is set tu true, which is the default for the debug BuildType.
    //  The jacoco.toolVersion specification used in modules that apply the jacoco gradle plugin is therefore not
    //  necessary for Android modules.
    val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
    commonExtension.testCoverage.jacocoVersion = libs.findVersion("jacoco").get().toString()
}

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
