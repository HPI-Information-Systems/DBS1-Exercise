package de.hpi.dbs1.grading

import de.hpi.dbs1.submitting.PackSubmissionTask
import org.gradle.api.logging.Logger
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.options.Option
import java.io.File
import java.nio.file.Files

abstract class LoadSubmissionTask : AbstractGradingTask() {
    @get:Input
    abstract val groupId: Property<String>

    @Option(option = "groupId", description = "Group ID")
    fun setGroupIdOption(value: String) {
        groupId.set(value)
    }

    @get:Internal
    val _groupId: String
        get() = (groupId.orNull ?: error("Please provide the groupId via --groupId=x"))

    @TaskAction
    fun loadSubmission(): String = loadSubmission(
        logger = logger,
        projectDir = project.projectDir,
        groupId = _groupId,
    )

    companion object {
        fun loadSubmission(
            logger: Logger,
            projectDir: File,
            groupId: String,
        ): String {
            val sourceDir = projectDir.resolve("src/main/")

            val groupDir = projectDir
                .resolve("submissions/submission-$groupId/")
                .also { require(it.isDirectory) { "submission missing \"submissions/submission-$groupId/\"" } }

            val implFile = groupDir.resolve(
                groupDir.resolve("chosenImplementation.txt")
                    .takeIf { it.exists() }
                    ?.let { Files.readString(it.toPath()) }
                    ?: error("missing chosen implementation")
            )
            val implLang =
                PackSubmissionTask.JVM_LANGUAGE_FILE_EXTENSION_MAPPINGS[implFile.extension]!!
            val targetDir = sourceDir.resolve(implLang)

            logger.quiet("Loading submission of group $groupId")

            val targetFile = targetDir.resolve(implFile.name)
            logger.debug("> Copying implementation file $implFile -> $targetFile")
            if (targetFile.exists()) {
                val backupFile = targetDir.resolve("${targetFile.name}.backup")
                Files.move(targetFile.toPath(), backupFile.toPath())
                logger.debug("> Replaced file $targetFile")
            } else {
                val tombstoneFile = targetDir.resolve("${targetFile.name}.delete")
                Files.createFile(tombstoneFile.toPath())
                logger.debug("> Created file $targetFile")
            }
            Files.copy(implFile.toPath(), targetFile.toPath())

            logger.quiet("Submission of group $groupId loaded")
            logger.quiet("Implementation language: $implLang")
            return implLang
        }
    }
}
