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

buildCache {
    local {
        directory = File(rootDir, ".gradle-build-cache")
        removeUnusedEntriesAfterDays = 7
    }
}

include(":server")

include(":composeApp")

include(":ui:add-content")
include(":ui:core")
include(":ui:home")
include(":ui:quiz")

include(":shared:core:build-info")
include(":shared:core:model")
include(":shared:core:utils-loading")
include(":shared:core:utils-network")
include(":shared:core:utils-validation")

include(":shared:functionality:project-defaults")

include(":shared:functionality:add-content:abstractions")
include(":shared:functionality:add-content:data")
include(":shared:functionality:add-content:entities")
include(":shared:functionality:add-content:usecases")
