package extensions

import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import org.gradle.api.GradleException
import org.gradle.api.Project

/**
 * Gets a param with the given [name] or null if this param does not exists.
 * It resolves the param in the following way:
 *
 * 1. From the project properties (e.g. cmd params)
 * 2. From the local.properties of the root project
 */
fun Project.param(name: String): String? {
    return (project.findProperty(name) as? String).alsoWhenNull {
        logger.warn("Could not find '$name' in cmd params")
    } ?: localProperties.getProperty(name).alsoWhenNull {
        logger.error("Could not find '$name' in local.properties")
    }
}

/**
 * Gets a param with the given [name] or fails the build if this param does not exists.
 * It resolves the param in the following way:
 *
 * 1. From the project properties (e.g. cmd params)
 * 2. From the local.properties of the root project
 */
fun Project.requireParam(name: String): String {
    return param(name) ?: throw GradleException(
        "Could not find mandatory '$name' in cmd params or local.properties",
    )
}

internal val Project.localProperties
    get() = gradleLocalProperties(providers = providers, projectRootDir = file("${rootProject.rootDir}"))

internal fun <T> T.alsoWhenNull(block: () -> Unit) = also {
    if (it == null) block()
}