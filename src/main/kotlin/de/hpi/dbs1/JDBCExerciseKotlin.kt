package de.hpi.dbs1

import java.sql.Connection
import java.util.logging.Logger

@ChosenImplementation(false)
class JDBCExerciseKotlin : JDBCExercise {

    val logger = Logger.getLogger(javaClass.simpleName)

    override fun createConnection(config: ConnectionConfig): Connection {
        TODO("Not yet implemented")
    }

    override fun execute(connection: Connection, keywords: String) {
        logger.info(keywords)

        TODO("Not yet implemented")
    }
}
