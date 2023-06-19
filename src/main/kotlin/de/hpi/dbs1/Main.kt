package de.hpi.dbs1

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.context
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.options.check
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.required
import com.github.ajalt.clikt.parameters.types.int
import com.github.ajalt.clikt.sources.PropertiesValueSource

class JDBCExerciseApp : CliktCommand(), ConnectionConfig {
    init {
        context {
            valueSource = PropertiesValueSource.from("database.properties")
        }
    }

    private val _host: String by option(
        "--host", envvar = "DB_HOST",
        help = "database hostname"
    ).required().check { it.isNotBlank() }
    override fun getHost() = _host

    private val _port: Int by option(
        "--port", envvar = "DB_PORT",
        help = "database port"
    ).int().required()
    override fun getPort() = _port

    private val _database: String by option(
        "--database", envvar = "DB_NAME",
        help = "database name"
    ).required().check { it.isNotBlank() }
    override fun getDatabase() = _database

    private val _username: String by option(
        "--username", envvar = "DB_USER",
        help = "database username"
    ).required().check { it.isNotBlank() }
    override fun getUsername() = _username

    val _password: String by option(
        "--password", envvar = "DB_PASSWORD",
        help = "database password"
    ).required().check { it.isNotBlank() }
    override fun getPassword() = _password

    private val searchKeyword: String by argument(
        "<SEARCH KEYWORDS>"
    )

    override fun run() {
        val implementation = getChosenImplementation()
        implementation.createConnection(this).use {
            println(it.metaData.url)
            it.metaData.apply {
                println("Connection JDBC URL=$url")
            }
            implementation.execute(it, searchKeyword)
        }
    }
}

fun main(args: Array<String>) = JDBCExerciseApp().main(args)
