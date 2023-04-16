package de.hpi.dbs1

import java.util.logging.Logger

@ChosenImplementation(false)
class JDBCExerciseKotlin : JDBCExercise {

    override fun execute(jdbcURL: String, keywords: String) {
        val logger = Logger.getLogger("JDBCExerciseKotlin")
        logger.info(jdbcURL)
        logger.info(keywords)

        TODO("Not yet implemented")
    }
}
