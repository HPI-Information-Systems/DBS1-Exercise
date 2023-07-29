package de.hpi.dbs1.grading

import org.gradle.api.file.RegularFileProperty
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.options.Option
import java.io.File
import java.util.zip.ZipEntry
import java.util.zip.ZipFile

abstract class UnpackSubmissionsTask : AbstractGradingTask() {
    @Internal
    val submissionsDir = project.projectDir.resolve("submissions/")
        .also { require(it.isDirectory) }

    @get:InputFile
    abstract val moodleArchiveFile: RegularFileProperty

    @Option(option = "moodleArchivePath", description = "Path to moodle submission archive file")
    fun setMoodleArchivePath(path: String) {
        moodleArchiveFile.set(submissionsDir.resolve(path))
    }

    @get:Input
    var skipExisting: Boolean = true

    companion object {
        val SUBMISSION_ZIP_REGEX =
            Regex("(.*?Group \\d+)-?.*?/exercise4-group[a-zA-Z 0-9]+.zip")
    }

    @TaskAction
    fun run() {
        val moodleExportFile = moodleArchiveFile.asFile.get()

        logger.quiet("Unpacking moodle archive: $moodleExportFile")
        var unknownGroupCounter = 0
        unzip(moodleExportFile) { entry ->
            if (entry.isDirectory) null
            else {
                val match = SUBMISSION_ZIP_REGEX.matchEntire(entry.name)
                if(match == null) {
                    logger.warn("Unknown group for submission \"$entry\"")
                    return@unzip null
                }
                val groupId = (
                    match.groupValues.getOrNull(1)
                        ?: error("unknown group for ${entry.name}")
//                        ?: "?${unknownGroupCounter++}"
                    ).uppercase()
                logger.quiet("Found submission for group $groupId")
                submissionsDir.resolve("submission-$groupId/")
                    .also { groupDir ->
                        if (!groupDir.list().isNullOrEmpty()) {
                            // not empty
                            if (skipExisting) return@unzip null
                            groupDir.deleteRecursively()
                        }
                        groupDir.mkdirs()
                    }
                    .resolve(entry.name.substringAfterLast('/'))
            }
        }
            .filter { it.extension == "zip" }
            .forEach { submission ->
                logger.info("Unpacking submission: $submission")
                unzip(submission)
                submission.delete()
            }
    }

    fun unzip(
        zipFile: File,
        targetDirectory: File = zipFile.parentFile,
        override: Boolean = false,
        fileAssigner: (ZipEntry) -> File? = { entry ->
            if (entry.isDirectory) null
            else targetDirectory.resolve(entry.name).also {
                it.parentFile.mkdirs()
            }
        },
    ): List<File> = buildList {
        ZipFile(zipFile).use { zip ->
            zip.stream().forEach { entry ->
                val unpackedFile = fileAssigner(entry) ?: return@forEach
                if (!unpackedFile.exists() || override) {
                    zip.getInputStream(entry).use { i ->
                        unpackedFile.outputStream().use { o ->
                            i.copyTo(o)
                        }
                    }
                }
                add(unpackedFile)
            }
        }
    }
}
