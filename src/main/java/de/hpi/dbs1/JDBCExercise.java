package de.hpi.dbs1;

import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;

public interface JDBCExercise {
	void execute(@NotNull String jdbcURL, @NotNull String keywords) throws SQLException;
}
