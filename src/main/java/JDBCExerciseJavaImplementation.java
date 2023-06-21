import de.hpi.dbs1.ChosenImplementation;
import de.hpi.dbs1.ConnectionConfig;
import de.hpi.dbs1.JDBCExercise;
import de.hpi.dbs1.entities.Actor;
import de.hpi.dbs1.entities.Movie;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

@ChosenImplementation(false)
public class JDBCExerciseJavaImplementation implements JDBCExercise {

	Logger logger = Logger.getLogger(this.getClass().getSimpleName());

	@Override
	public Connection createConnection(@NotNull ConnectionConfig config) throws SQLException {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public List<Movie> queryMovies(
		@NotNull Connection connection,
		@NotNull String keywords
	) throws SQLException {
		logger.info(keywords);
		List<Movie> movies = new ArrayList<>();

		/*
		var myMovie = new Movie("??????????", "My Movie", 2023, Set.of("Indie"));
		myMovie.actorNames.add("Myself");
		movies.add(myMovie);
		*/

		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public List<Actor> queryActors(
		@NotNull Connection connection,
		@NotNull String keywords
	) throws SQLException {
		logger.info(keywords);
		List<Actor> actors = new ArrayList<>();

		throw new UnsupportedOperationException("Not yet implemented");
	}
}
