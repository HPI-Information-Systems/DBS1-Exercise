package de.hpi.dbs1.grading

import de.hpi.dbs1.submitting.PackSubmissionTask
import org.gradle.api.logging.Logger
import org.gradle.api.tasks.TaskAction
import java.nio.file.StandardCopyOption
import java.io.File
import java.nio.file.Files

abstract class UnloadSubmissionTask : AbstractGradingTask() {
    @TaskAction
    fun unloadSubmission() = unloadSubmission(
        logger = logger,
        projectDir = project.projectDir
    )

    companion object {
        fun unloadSubmission(
            logger: Logger,
            projectDir: File,
        ) {
            val sourceDir = projectDir.resolve("src/main/")

            logger.quiet("Unloading current submission")
            PackSubmissionTask.JVM_LANGUAGE_SRC_DIRS.forEach { impl ->
                val targetDir = sourceDir.resolve(impl)

                targetDir.listFiles()!!.forEach { file ->
                    val originalFile = file.resolveSibling(file.nameWithoutExtension)
                    when (file.extension) {
                        "backup" -> {
                            Files.move(
                                file.toPath(),
                                originalFile.toPath(),
                                StandardCopyOption.REPLACE_EXISTING
                            )
                            logger.info("> Restored file $originalFile")
                        }

                        "delete" -> {
                            Files.delete(file.toPath())
                            Files.deleteIfExists(originalFile.toPath())
                            logger.info("> Removed file $originalFile")
                        }
                    }
                }
            }
            logger.quiet("Submission unloaded")
        }
    }
}
