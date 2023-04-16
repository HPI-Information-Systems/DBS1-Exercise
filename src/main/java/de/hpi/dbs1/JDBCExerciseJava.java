package de.hpi.dbs1;

import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.logging.Logger;

@ChosenImplementation(false)
public class JDBCExerciseJava implements JDBCExercise {

    @Override
    public void execute(@NotNull String jdbcURL, @NotNull String keywords) throws SQLException {
        var logger = Logger.getLogger("JDBCExerciseJava");
        logger.info(jdbcURL);
        logger.info(keywords);

        throw new UnsupportedOperationException("Not yet implemented");
    }
}
