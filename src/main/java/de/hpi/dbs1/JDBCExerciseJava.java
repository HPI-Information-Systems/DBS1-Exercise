package de.hpi.dbs1;

import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

@ChosenImplementation(false)
public class JDBCExerciseJava implements JDBCExercise {

	Logger logger = Logger.getLogger(this.getClass().getSimpleName());

	@Override
	public Connection createConnection(@NotNull ConnectionConfig config) throws SQLException {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void execute(@NotNull Connection connection, @NotNull String keywords) throws SQLException {
		logger.info(keywords);

		throw new UnsupportedOperationException("Not yet implemented");
	}
}
