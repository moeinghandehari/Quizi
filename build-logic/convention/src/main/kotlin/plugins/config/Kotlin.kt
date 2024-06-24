package plugins.config

import AppBuildConfig
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test
import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinProjectExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet
import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig
import org.jetbrains.kotlin.gradle.tasks.KotlinCompileCommon
import plugins.extensions.sourceSets

internal fun Project.configureKotlinMultiplatform() {
    configure<KotlinMultiplatformExtension> {
        applyDefaultHierarchyTemplate()

        @OptIn(ExperimentalWasmDsl::class)
        wasmJs {
            browser {
                commonWebpackConfig {
                    outputFileName = "composeApp.js"
                    devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                        static = (static ?: mutableListOf()).apply {
                            // Serve sources to debug inside browser
                            add(project.projectDir.path)
                        }
                    }
                }
            }
            binaries.executable()
        }

        androidTarget {
            compilations.all {
                kotlinOptions {
                    jvmTarget = AppBuildConfig.jvmToolchain.toString()
                }
            }
        }

        jvm("desktop")

        listOf(
            iosX64(),
            iosArm64(),
            iosSimulatorArm64(),
        ).forEach { iosTarget ->
            iosTarget.binaries.framework {
                baseName = "ComposeApp"
                isStatic = true
            }
        }

        sourceSets {
            commonMain.dependencies {
                // implementation(catalog.findLibrary("kotlin.coroutines.core").get())
            }

            commonTest.dependencies {
                // implementation(catalog.findLibrary("kotlin.test").get())
                // implementation(catalog.findLibrary("kotlin.coroutines.test").get())
                // implementation(catalog.findLibrary("turbine").get())
            }

            // Opt-in for native coroutines
            all {
                languageSettings.optIn("kotlin.experimental.ExperimentalObjCName")
            }
        }

        // Kotlin by default uses a (not so unique) `unique_name` in the pattern `<moduleName>_commonMain` in the
        //  modules manifest leading to a `duplicate library name` error, if the same module name is used multiple
        //  times. This block makes the name actually unique by prepending the project group (aka the path where the
        //  module is located).
        metadata {
            compilations.configureEach {
                if (name == KotlinSourceSet.COMMON_MAIN_SOURCE_SET_NAME) {
                    compileTaskProvider.configure {
                        if (this is KotlinCompileCommon) {
                            moduleName.set("${project.group}.${moduleName.get()}")
                        }
                    }
                }
            }
        }
    }

    // Building KMP sometimes fails with `Task 'testClasses' not found in project`
    // https://stackoverflow.com/questions/33132996/android-skip-gradle-testclasses-task-for-a-dependency-project
    tasks.maybeCreate("testClasses")

    configureCommonKotlin()
}

internal fun Project.configureKotlinAndroid() {
//    val implementation by configurations
//    val testImplementation by configurations
//    dependencies {
//        implementation(catalog.findLibrary("kotlin.coroutines.core").get())
//
//        testImplementation(catalog.findLibrary("kotlin.test").get())
//        testImplementation(catalog.findLibrary("kotlin.coroutines.test").get()) {
//            because("Testing of Kotlin Coroutines")
//        }
//        testImplementation(catalog.findLibrary("turbine").get()) {
//            because("Testing of Kotlin Flows")
//        }
//    }

    configureCommonKotlin()
}

private fun Project.configureCommonKotlin() {
    extensions.configure<KotlinProjectExtension> {
        jvmToolchain(AppBuildConfig.jvmToolchain)
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = freeCompilerArgs + "-opt-in=kotlin.RequiresOptIn"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
        testLogging {
            events(
                TestLogEvent.PASSED,
                TestLogEvent.SKIPPED,
                TestLogEvent.FAILED,
            )
        }
    }
    // configureJacocoKotlin()
}