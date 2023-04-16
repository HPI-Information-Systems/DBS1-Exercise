import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

plugins {
    kotlin("jvm") version "1.8.20"
    java
    application

    id("com.github.ben-manes.versions") version "0.46.0"
    id("org.jetbrains.dokka") version "1.8.10"
    idea
}

group = "de.hpi.dbs1"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    dokkaHtmlPlugin("org.jetbrains.dokka:kotlin-as-java-plugin:1.8.10")

    implementation("org.postgresql:postgresql:42.6.0")

    implementation("com.github.ajalt.clikt:clikt:3.5.2")

    testImplementation(kotlin("test-junit5"))
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.9.2")
    testImplementation("io.kotest:kotest-assertions-core:5.5.5")
}

application {
    mainClass.set("$group.MainKt")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(19))
    }
}

tasks {
    withType<JavaExec> {
        enableAssertions = true
        systemProperty("java.util.logging.config.file", "logging.properties")
    }
    test {
        enableAssertions = true
        testLogging {
            showStandardStreams = true
        }
        useJUnitPlatform()
    }
    withType<KotlinJvmCompile> {
        kotlinOptions {
            apiVersion = "1.8"
            languageVersion = "1.8"
            freeCompilerArgs = listOf("-Xcontext-receivers")
        }
    }

    withType<DependencyUpdatesTask> {
        val unstable = Regex("^.*?(?:alpha|beta|unstable|ea|rc).*\$", RegexOption.IGNORE_CASE)
        rejectVersionIf {
            candidate.version.matches(unstable)
        }
    }
}

idea {
    module {
        isDownloadJavadoc = true
        isDownloadSources = true
    }
}
