package plugins.config

import io.gitlab.arturbosch.detekt.Detekt
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getValue
import org.gradle.kotlin.dsl.provideDelegate
import org.gradle.kotlin.dsl.register
import org.gradle.kotlin.dsl.withType
import plugins.extensions.catalog

internal fun Project.configureDetektProject() {
    with(pluginManager) {
        apply("io.gitlab.arturbosch.detekt")
    }

    val detektPlugins by configurations
    dependencies {
        detektPlugins(catalog.findLibrary("detekt.formatting").get())
        detektPlugins(catalog.findLibrary("detekt.composeRules").get())
    }

    tasks.withType<Detekt>().configureEach {
        setSource(projectDir)
        include("**/*.kt", "**/*.kts")
        exclude("**/resources/**", "**/build/**")

        config.setFrom(files("$rootDir/config/detekt/detekt.yml"))
        buildUponDefaultConfig = true
        parallel = true
    }

    tasks.register<Detekt>(name = "detektAutoCorrect") {
        autoCorrect = true
        ignoreFailures = true
    }

    // TODO Check if needed?
//    There is no `check` task on root project level by default. Therefore Detekt,
//    which is configured on root project level, would not run when `gradle check` is executed.
//    To include it, a `check` task is explicitly registered.
//    tasks.register("check") {
//        group = "verification"
//    }
}
