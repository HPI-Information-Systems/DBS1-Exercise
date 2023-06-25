import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

plugins {
    kotlin("jvm") version "1.8.22"
    idea
}

group = "de.hpi.dbs1"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
}

kotlin {
    jvmToolchain(17)
}

tasks {
    withType<KotlinJvmCompile> {
        kotlinOptions {
            apiVersion = "1.8"
            languageVersion = "1.8"
        }
    }
}

idea {
    module {
        isDownloadJavadoc = true
        isDownloadSources = true
    }
}
