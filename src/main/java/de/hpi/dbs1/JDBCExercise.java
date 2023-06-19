package de.hpi.dbs1;

import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.SQLException;

public interface JDBCExercise {

	/**
	 * Creates a connection to the database.
	 * See <a href="https://jdbc.postgresql.org/documentation/use/">Postgres JDBC Driver Documentation</a>
	 * for examples on how to do this.
	 *
	 * @param config all relevant connection details
	 * @return an open connection
	 * @throws SQLException if the connection could not be established
	 */
	Connection createConnection(@NotNull ConnectionConfig config) throws SQLException;

	/**
	 * Searches the database
	 * @param connection an open database connection
	 * @param keywords the keywords for which to search
	 * @throws SQLException if the query fails
	 */
	void execute(@NotNull Connection connection, @NotNull String keywords) throws SQLException;
}
