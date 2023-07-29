package de.hpi.dbs1.grading

import org.gradle.api.DefaultTask

abstract class AbstractGradingTask : DefaultTask() {
    init {
        group = "grading"
        enabled = project.hasProperty("tutor")
    }
}
