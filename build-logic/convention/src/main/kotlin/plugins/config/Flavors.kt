package plugins.config

import AppBuildConfig
import AppBuildConfig.BrandFlavorDimension.productFlavors
import AppBuildConfig.flavorDimensions
import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.aggregateTestCoverage

internal fun Project.configureFlavors(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply {
        AppBuildConfig.flavorDimensions.forEach { dimension ->
            flavorDimensions += dimension.name
            productFlavors {
                dimension.productFlavors.forEach { flavor ->
                    create(flavor.name) {
                        aggregateTestCoverage.set(flavor.aggregateTestCoverage)
                    }
                }
            }
        }
    }
}