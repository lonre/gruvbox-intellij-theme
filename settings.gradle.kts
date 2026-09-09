import org.jetbrains.intellij.platform.gradle.extensions.intellijPlatform

rootProject.name = "Gruvbox Islands Theme"

pluginManagement {
    plugins {
        id("org.jetbrains.changelog") version "2.5.0"
    }

    repositories {
        gradlePluginPortal()
    }
}

plugins {
    id("org.jetbrains.intellij.platform.settings") version "2.18.1"
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositories {
        mavenCentral()

        intellijPlatform {
            defaultRepositories()
        }
    }
}
