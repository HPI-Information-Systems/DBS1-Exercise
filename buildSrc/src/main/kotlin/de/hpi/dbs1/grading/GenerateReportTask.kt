package de.hpi.dbs1.grading

import org.gradle.api.tasks.TaskAction
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import java.io.File
import java.io.PrintWriter
import java.nio.file.Files
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.xml.parsers.DocumentBuilderFactory

abstract class GenerateReportTask : AbstractGradingTask() {
    @TaskAction
    fun run() {
        val cmdExecutor = Executors.newSingleThreadExecutor()

        val submissionsDir = project.projectDir.resolve("submissions/")

        logger.quiet("Cleaning workspace...")
        UnloadSubmissionTask.unloadSubmission(
            logger = logger,
            projectDir = project.projectDir,
        )

        var groupDirs = submissionsDir
            .listFiles { file ->
                file.isDirectory && file.name.startsWith("submission-")
            }!!
            .toList()
        if (submissionsDir.resolve("progress").exists()) {
            val lastValidated = submissionsDir.resolve("progress").readText()
            logger.quiet("Continuing interrupted execution")
            logger.quiet("Last validated submission \"$lastValidated\"")
            // continue execution
            groupDirs = groupDirs
                .dropWhile { groupDir ->
                    logger.quiet("Skipping \"${groupDir.name}\"")
                    groupDir.name != lastValidated
                }
                .drop(1)
        }

        logger.quiet("Validating submissions: \n${groupDirs.map { it.name }}")
        val reportFile = submissionsDir.resolve("report.tsv")
        PrintWriter(reportFile).use { crw ->
            crw.println("group\tchosenImplementation\texecutedTests\tfailedTests\tskippedTests\ttotalTime\tnote")

            groupDirs.forEach { groupDir ->
                val groupId = groupDir.name.substringAfter("submission-")
                withLoadedSubmission(groupId) { chosenImpl ->
                    val submissionReportFile = groupDir.resolve("testReport.txt")
                    PrintWriter(submissionReportFile).use { srw ->
                        validateSubmission(srw, cmdExecutor).apply {
                            crw.println("$groupId\t$chosenImpl\t$executedTests\t$failedTests\t$skippedTests\t$totalTime\t")
                        }
                    }
                    logger.quiet("Submission report written")
                }
                submissionsDir.resolve("progress").writeText(groupDir.name)
            }
        }
        submissionsDir.resolve("progress").delete()
        logger.quiet("Combined report written to $reportFile")

        cmdExecutor.shutdownNow()
    }

    private fun validateSubmission(writer: PrintWriter, cmdExecutor: ExecutorService): TestResults {
        logger.quiet("> Testing...")
        cmdExecutor.runCmd(
            "./gradlew.bat test --build-cache -q",
            ignoreExitCode = true
        )
        logger.quiet("> Tests completed")

        logger.quiet("> Finding test reports...")
        val testReportFiles = project.projectDir.resolve("build/test-results/test/")
            .listFiles { file ->
                file.isFile
                && file.name.startsWith("TEST-")
                && file.extension == "xml"
            }!!
            .distinct()

        return parseTestReports(testReportFiles)
            .apply {
                writer.println("executed=$executedTests")
                writer.println("failed=$failedTests")
                writer.println("skipped=$skippedTests")
                writer.println("time=$totalTime")
                tests.forEach {
                    writer.println("${it.testClass}\t${it.testName}\t${it.executionTime}\t${it.failureStatus}")
                }
            }
            .also {
                testReportFiles.forEach {
                    Files.deleteIfExists(it.toPath())
                }
            }
    }

    private fun parseTestReports(testReportFiles: Collection<File>): TestResults {
        val factory = DocumentBuilderFactory.newInstance()
        val builder = factory.newDocumentBuilder()
        var executedTests = 0
        var failedTests = 0
        var skippedTests = 0
        var totalTime = 0.0f
        val tests = ArrayList<TestResults.TestResult>()

        logger.quiet("> Parsing test reports...")
        testReportFiles.forEach { testReport ->
            val xmlDocument = builder.parse(testReport)
            xmlDocument.getElementsByTagName("testsuite").forEach { testSuite ->
                executedTests += testSuite.getAttribute("tests").toInt()
                failedTests += testSuite.getAttribute("failures").toInt()
                failedTests += testSuite.getAttribute("errors").toInt()
                skippedTests += testSuite.getAttribute("skipped").toInt()
                totalTime += testSuite.getAttribute("time").toFloat()

                testSuite.childNodes.forEach {
                    if (it.nodeName == "testcase") {
                        tests.add(
                            TestResults.TestResult(
                                testClass = it.getAttribute("classname"),
                                testName = it.getAttribute("name"),
                                executionTime = it.getAttribute("time").toFloat().toInt(),
                                failureStatus = it.childNodes
                                    .toList().firstOrNull { errorNode ->
                                        !errorNode.nodeName.startsWith('#')
                                    }?.nodeName
                                    ?: ""
                            )
                        )
                    }
                }
            }
        }
        return TestResults(executedTests, failedTests, skippedTests, totalTime.toInt(), tests)
    }

    data class TestResults(
        val executedTests: Int,
        val failedTests: Int,
        val skippedTests: Int,
        val totalTime: Int,
        val tests: List<TestResult>
    ) {
        data class TestResult(
            val testClass: String,
            val testName: String,
            val executionTime: Int,
            val failureStatus: String
        )
    }

    private fun NodeList.forEach(callback: (Node) -> Unit) {
        (0 until length).forEach { i ->
            callback(item(i))
        }
    }

    private fun NodeList.toList(): List<Node> = buildList {
        this@toList.forEach { add(it) }
    }

    private fun Node.getAttribute(attribute: String): String =
        attributes.getNamedItem(attribute)?.nodeValue
            ?: error("attribute \"$attribute\" not found")


    private fun withLoadedSubmission(
        groupId: String,
        block: (implLang: String?) -> Unit
    ) {
        logger.quiet("Processing submission of group $groupId")

        val implLang: String = LoadSubmissionTask.loadSubmission(
            logger = logger,
            projectDir = project.projectDir,
            groupId = groupId,
        )

        block(implLang)

        UnloadSubmissionTask.unloadSubmission(
            logger = logger,
            projectDir = project.projectDir,
        )
    }

    private fun ExecutorService.runCmd(
        command: String,
        ignoreExitCode: Boolean = false
    ): List<String> {
        logger.info("> Running \"$command\"")
        val process = Runtime.getRuntime().exec(command)
        val processOutput = process.inputReader()
        val output = submit<List<String>> {
            buildList {
                processOutput.use {
                    it.lines().forEach(::add)
                }
            }
        }
        process.waitFor().also { exitCode ->
            if (!ignoreExitCode) {
                require(exitCode == 0) { "execution failed $exitCode" }
            }
        }
        return output.get()
    }
}
