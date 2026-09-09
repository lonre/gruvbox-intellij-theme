import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.tasks.bundling.AbstractArchiveTask
import org.gradle.api.tasks.bundling.Jar
import org.jetbrains.intellij.platform.gradle.tasks.ComposedJarTask

plugins {
    id("org.jetbrains.intellij.platform")
    id("org.jetbrains.changelog")
}

dependencies {
    intellijPlatform {
        intellijIdea("2025.3")
    }
}

intellijPlatform {
    buildSearchableOptions = false

    pluginConfiguration {
        ideaVersion {
            sinceBuild = "253"
        }
    }

    pluginVerification {
        ides {
            current()
        }
    }

    publishing {
        token = providers.environmentVariable("JETBRAINS_MARKETPLACE_TOKEN")
            .orElse(providers.environmentVariable("intellij_publish_token"))
    }
}

tasks {
    withType<AbstractArchiveTask>().configureEach {
        isPreserveFileTimestamps = false
        isReproducibleFileOrder = true
    }

    named<Jar>("jar") {
        manifest = project.the<JavaPluginExtension>().manifest {
            attributes["Manifest-Version"] = "1.0"
        }
    }

    named<ComposedJarTask>("composedJar") {
        manifest = project.the<JavaPluginExtension>().manifest {
            attributes["Manifest-Version"] = "1.0"
        }
    }
}
