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

include(":ui:core")
include(":ui:add-content")
include(":ui:quiz")

include(":shared:core:model")
include(":shared:core:utils-network")

include(":shared:functionality:project-defaults")

include(":shared:functionality:add-data:abstractions")
include(":shared:functionality:add-data:data")
include(":shared:functionality:add-data:entities")
include(":shared:functionality:add-data:usecases")
