package de.hpi.dbs1

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument

class JDBCExerciseApp : CliktCommand() {
    val jdbcURL: String by argument(
        "<JDBC-URL>",
        help = "see https://jdbc.postgresql.org/documentation/use/ - e.g. jdbc:postgresql://localhost:5432/databasename?user=username&password=password"
    )
    val searchKeyword: String by argument(
        "<SEARCH KEYWORDS>"
    )

    override fun run() {
        getChosenImplementation()
            .execute(jdbcURL, searchKeyword)
    }
}

fun main(args: Array<String>) = JDBCExerciseApp().main(args)
