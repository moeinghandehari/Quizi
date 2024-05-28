rootProject.name = "Quizi"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    includeBuild("build-logic")
    repositories {
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

plugins {
    // JVM toolchain auto-provisioning (see https://docs.gradle.org/current/userguide/toolchains.html#sec:provisioning)
    id("org.gradle.toolchains.foojay-resolver-convention") version ("0.7.0")
}

buildCache {
    local {
        directory = File(rootDir, ".gradle-build-cache")
        removeUnusedEntriesAfterDays = 7
    }
}

include(":server")

include(":composeApp")

include(":shared")
include(":shared:core:model")
include(":shared:core:utils-network")

include(":feature")

include(":feature:project-defaults")

include(":feature:quiz-input:abstractions")
include(":feature:quiz-input:data")
include(":feature:quiz-input:entities")
include(":feature:quiz-input:usecases")
