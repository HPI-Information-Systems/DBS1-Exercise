# DBS I Exercise Framework

This is the exercise repository for the database systems I course for IT systems engineering bachelors degree @ Hasso Plattner Institute Potsdam.

_TODO TODO TODO_
The exercises will cover different database datastructures and  algorithms, which you will have to implement in either Java or Kotlin (please give us feedback if you want to work in another JVM language, like Groovy or Scala).
We chose JVM (Java Virtual Machine) languages as a base for all implementations in this course for ease of development and quick setup, and to give you the chance to test out a new popular programming language in your studies, if you haven't developed in Java or Kotlin yet.
The build process is using Gradle and is already configured to use the currently latest long-term release JVM 17.

For each of the exercises, we already provide a package with empty classes as either `.java` (for Java) or `.kt` (for Kotlin) files.
You can choose to implement either of them and our JUnit tests will pick the correct one to test, but please keep all your code which you write for your solution exactly in these packages.
Finally, for easier submission, we included a build task, which packs your implementation files (and only these in the belonging packages) as `.zip` files, which you can upload in Moodle.

### Setup

This repository contains a Gradle project, which can be used standalone with a command line and a text editor, or preferably with an IDE like IntelliJ Idea (free student license), VSCode or Eclipse (IDEs with Gradle integration make running build tasks easier and have some nice development shortcuts).
We suggest that you use IntelliJ Idea if this is your first time working with Java projects and already integrated the Idea Gradle plugin in the buildscript.

### Using Git

We suggest that you fork the [GitHub repository](https://github.com/HPI-Information-Systems/DBS2-Exercise), so you can track your own changes.
If you do not intend to use Git, you can also clone or simply download the repository.
To update your fork after the release of new exercise, you can synchronize the repository on the GitHub website.
Alternatively, you can configure an upstream repository and fetch the upstream changes via the terminal.
See this [help article](https://github.com/HPI-Information-Systems) for both methods.

### First step

Please configure your exercise group in the `gradle.properties` configuration file - the build script will require `groupIdentifier` to not be blank to execute any build task.

### Running with IDE

Simply open this folder in the IDE and it should automatically get picked up as a Gradle project, which should then index all containing files and start the Gradle build daemon.
The most important build tasks we want to use are:
- `gradle test` to start the provided test suite test your solutions.
- `gradle packExercise<X>` to pack your implementation for exercise <X> in a `.zip` file (will be written in the project root folder) for submission.

### Running without IDE

You can also use the Gradle build process without IDE by running each required build task using the provided `./gradlew` script.
Run all described build tasks from the other tutorial as appended CLI arguments, e.g. `./gradlew test`.
See the [Gradle Wrapper documentation](https://docs.gradle.org/current/userguide/gradle_wrapper.html#sec:using_wrapper) for more details.

## Exercise tests

We use [JUnit5](https://junit.org/junit5/docs/current/user-guide/) with [Kotest Assertions](https://kotest.io/docs/assertions/assertions.html) for unit testing the most important methods of each exercise.
Feel free to write tests for edge-cases which you found while implementing your exercise solutions (not mandatory).
We will test you submissions with a bigger test set and different input data to check for correctness.
