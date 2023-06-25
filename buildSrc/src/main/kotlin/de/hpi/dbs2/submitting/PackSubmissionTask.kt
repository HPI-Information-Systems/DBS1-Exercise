package de.hpi.dbs2.submitting

import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import org.gradle.api.DefaultTask
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.OutputDirectory
import java.nio.file.*
import kotlin.io.path.extension
import kotlin.io.path.isRegularFile
import kotlin.io.path.listDirectoryEntries

abstract class PackSubmissionTask : DefaultTask() {
    @get:InputDirectory
    abstract val sourceDirectory: DirectoryProperty

    @get:OutputDirectory
    abstract val targetDirectory: DirectoryProperty

    @get:Input
    abstract val groupIdentifier: Property<String>

    @get:Internal
    val _groupId: String
        get() = groupIdentifier.orNull
            ?.takeUnless { it.isBlank() }
            ?: error("Please set your group identifier in the gradle.properties file")

    companion object {
        val JVM_LANGUAGE_SRC_DIRS = listOf("kotlin", "java", "groovy", "scala")
    }

    init {
        group = "submission"
        groupIdentifier.set(project.property("groupIdentifier") as String)
        sourceDirectory.set(project.file("src/main/"))
        targetDirectory.set(project.file("submissions/"))
    }

    @TaskAction
    fun pack() {
        logger.quiet("Packing submission using groupIdentifier=$_groupId")
        val submissionFile = targetDirectory.get()
            .file("exercise4-group$_groupId.zip").asFile

        Files.deleteIfExists(submissionFile.toPath())
        FileSystems.newFileSystem(submissionFile.toPath(), mapOf("create" to "true")).use { zipFS ->
            Files.writeString(
                zipFS.getPath("groupId.txt"),
                _groupId
            )

            val chosenImplementationFile = JVM_LANGUAGE_SRC_DIRS
                .flatMap { language ->
                    val sourcePath = sourceDirectory.get()
                        .dir(language)
                        .asFile.toPath()
                    if (Files.isDirectory(sourcePath)) {
                        sourcePath.listDirectoryEntries()
                            .filter { it.isRegularFile() }
                            .filter { it.extension == "kt" || it.extension == "java" }
                    } else listOf()
                }
                .firstOrNull { source ->
                    Files.lines(source)
                        .anyMatch { line ->
                            line.trimStart().startsWith("@ChosenImplementation")
                            && line.contains("true")
                        }
                }
                ?: error("Chose an implementation for your exercise by setting @ChosenImplementation(true)")

            logger.quiet(
                "Selected \"${
                    sourceDirectory.get().asFile.toPath().relativize(chosenImplementationFile)
                }\" as chosen implementation"
            )
            Files.writeString(
                zipFS.getPath("chosenImplementation.txt"),
                chosenImplementationFile.fileName.toString()
            )
            Files.copy(
                chosenImplementationFile,
                zipFS.getPath(chosenImplementationFile.fileName.toString())
            )
        }

        logger.quiet(
            """
                Submission has been written to: $submissionFile
                Please upload this file to Moodle!
            """.trimIndent()
        )
    }
}
