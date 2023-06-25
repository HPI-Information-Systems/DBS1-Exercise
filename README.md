# DBS I Exercise Framework

This is the exercise repository for the database systems I course for IT systems engineering bachelors degree @ Hasso Plattner Institute Potsdam.

The exercise will cover how to programmatically connect to a DBMS and query data.
In this case, we want you to use the Java Database Connectivity (JDBC) which is a stable and often used database API for JVM-based programming languages (exists since 1997).
We chose JVM (Java Virtual Machine) languages as a base for all implementations in this course for ease of development and quick setup, and to give you the chance to test out a new popular programming language in your studies, if you haven't developed in Java or Kotlin yet.

Your assignment is to implement a simple application which connects to your PostgreSQL instance and queries movies and actors / actresses with a title / name matching the given keyword from the IMDB dataset. For that, you can program in either Java or Kotlin (please give us feedback if you want to work in another JVM language, like Groovy or Scala).

The build process is using Gradle and uses the (currently) latest long-term release Java 17 / Kotlin 1.8.22. Don't worry, our configuration should make setup and running easy for you, so that you don't have to figure out how this works!

## Setup

This repository contains a Gradle project, which can be used standalone with a command line and a text editor, or preferably with an IDE like IntelliJ Idea (free student license) or VSCode (or Netbeans / Eclipse) - IDEs with Gradle integration make running build tasks easier and have some nice development shortcuts.
We suggest that you use IntelliJ Idea if this is your first time working with Java projects or if you want easy plug and play with the Gradle build tasks. Of course, you can ask us on Moodle if you need help with you setup but we might not be prepared if you try out other IDEs.

### Initial steps

Please configure your exercise group in the `gradle.properties` configuration file - the build script will require `groupIdentifier` to not be blank to execute any build task. Fill in the number of your group after the = (e.g. `groupIdentifier=42`)!

Next configure your database connection details in the `database.properties` file. You could also provide these as command line arguments or environment variables, but this should be the easiest way.

### Find the code

Next you can decide in which language you want to implement the exercise. We provided a single file in which you have to write your solution.

 - for Java `/src/main/java/JDBCExerciseJavaImplementation.java`.
 - for Kotlin `/src/main/kotlin/JDBCExerciseKotlinImplementation.kt`.

Once you decided on your used language, check the top of the class where you find a `@ChosenImplementation(false)` annotation - replace `false` with `true`. Only the class with this annotation set to true will be executed by the application and tests, and only its containing file will get packed for the submission.

## Execution
### Running with IDE

Simply open this folder in your IDE and it should automatically get picked up as a Gradle project (or via open / import project), which should then index all containing files and start the Gradle build daemon.

The most important build tasks we want to use are:
- `gradle run --args="<search keyword>"` to start your program with the given keyword.
- `gradle test` to start the provided test suite to test your solutions.
- `gradle packSubmission` to pack your implementation for submission.

If you have a Gradle plugin in your IDE (icon with an elephant), you should find these task there. You can also provide run arguments on these which is needed for `gradle run`.

### Running without IDE

You can also use the Gradle build process without IDE by running each required build task using the provided `./gradlew` script.
Run all described build tasks from the other tutorial as command line arguments, e.g. `./gradlew test`.
See the [Gradle Wrapper documentation](https://docs.gradle.org/current/userguide/gradle_wrapper.html#sec:using_wrapper) for more details.

## Working on your implementation

You have your chosen implementation file open? No matter which language you picked, both contain a class which implements the [JDBCExercise](src/main/java/de/hpi/dbs1/JDBCExercise.java) interface. Check it out to see what each method is supposed to do!

You will see that there are 3 tasks to do, which get harder with each time:
1. create a connection to the database
2. query movies matching the keyword
3. query actors matching the keyword

The later two also require [entity objects](src/main/java/de/hpi/dbs1/entities) which will contain relational results from your queries. How you query the database is up to you, but at the end, you have to return lists of the required objects from the methods.

The lecture on Wednesday 28th of June should give you a short introduction to JDBC. Additionally you can read details about JDBC in the [PostgreSQL JDBC Documentation](https://jdbc.postgresql.org/documentation/use/) , though a lot them are not needed for this exercise. All you need to do is use the API to connect to your database, create `PreparedStatement` objects for queries, and read the `ResultSet` to get the query results.

### Test your implementation

We provided you some [JUnit tests](src/test/java/de/hpi/dbs1/JDBCExerciseImplementationTests.java) for testing the basic functionality of each method. You can start working on your solution and cross-check with each test, if your code does the correct things. They should generate helpful insights into which data is missing or what sort order was wrong. Re-read the documentation details in JDBCExercise when some parts of the result are incorrect - you might have missed a detail there (if you are sure your solution must be correct, we might have made a mistake ourselves - feedback is welcome on Moodle). You can see what the results should be by looking here [Movies](src/test/kotlin/de/hpi/dbs1/fixtures/Movies.kt) / [Actors](src/test/kotlin/de/hpi/dbs1/fixtures/Actors.kt).
Once all tests are green you are probably ready to submit.

Feel free to write tests for edge-cases which you found while implementing your exercise solutions, if you feel comfortable doing it (not mandatory). We will test you submissions with a bigger test set and different input data to check for correctness!

### Running the application

You might have noticed, that your implementation results in a simple app which can be run using `gradle run --args="<search keyword>"`. This prints the results for the found movies and actors on your console. You can play around with these while working on your implementation if you prefer this to the tests. You can also use the provided logger or println from your chosen language or use debug tools to check the execution of your code.

Under the hood Gradle runs the [`main(args)` method](src/main/kotlin/de/hpi/dbs1/Main.kt) to start an instance of the `JDBCExerciseApp`. The app class handles command line argument processing and then uses your provided implementation to connect to the database, do the two queries and print their results. Don't worry about Kotlin - both languages are inter-compatible, but we wrote the most relevant parts for you in Java; the Kotlin side contains mostly utilities for the execution of the application and tests.

### Submission

Use `gradle packSubmission` to pack your chosen implementation file into a `.zip` (with some additional data). It will be written into the `submissions` folder in your project. Please upload exactly this file to Moodle!
