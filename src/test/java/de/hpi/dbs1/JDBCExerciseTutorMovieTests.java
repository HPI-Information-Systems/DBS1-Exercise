package de.hpi.dbs1;

import de.hpi.dbs1.entities.Actor;
import de.hpi.dbs1.entities.Movie;
import org.junit.jupiter.api.*;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

import static de.hpi.dbs1.fixtures.Actors.*;
import static de.hpi.dbs1.fixtures.Movies.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class JDBCExerciseTutorMovieTests {

	static JDBCExercise implementation;
	static ConnectionConfig config;

	@BeforeAll
	static void setUp() {
		implementation = ChosenImplementationKt.getChosenImplementation();
		config = new PropertiesConnectionConfig(new File("database.properties"));
		Assertions.assertFalse(config.getUsername().isBlank(), "please provide your database username in the \"database.properties\" file");
		Assertions.assertFalse(config.getPassword().isBlank(), "please provide your database password in the \"database.properties\" file");
	}

	@Test
	@Order(2)
	@DisplayName("Movie query: Transformers Interstellar")
	void testQueryMoviesTI() throws SQLException {
		try(var connection = implementation.createConnection(config)) {
			List<Movie> results = implementation.queryMovies(connection, "Transformers Interstellar");

			Assertions.assertIterableEquals(List.of(TIB_2002, TIOP_2014), results);
		}
	}

	@Test
	@Order(2)
	@DisplayName("Movie query: Attack of the 50 Foot")
	void testQueryMoviesA50F() throws SQLException {
		try(var connection = implementation.createConnection(config)) {
			List<Movie> results = implementation.queryMovies(connection, "Attack of the 50 Foot");

			Assertions.assertIterableEquals(List.of(AOT5FCFOS_1998, AOT5FH_2012, AOT5FK_2011, AOT5FMM_1999, AOT5FW_1958), results);
		}
	}

	@Test
	@Order(2)
	@DisplayName("Movie query: 9/11 Truth")
	void testQueryMovies911T() throws SQLException {
		try(var connection = implementation.createConnection(config)) {
			List<Movie> results = implementation.queryMovies(connection, "9/11 Truth");

			Assertions.assertIterableEquals(List.of(THSO_2011), results);
			Assertions.assertEquals(THSO_2011.actorNames, results.get(0).actorNames, "\"9/11 Truth: Hollywood Speaks Out\" did not contain the correct actors");
		}
	}

	@Test
	@Order(2)
	@DisplayName("Movie query: Abducted by a Female Alien with Red Eyes")
	void testQueryMoviesAFARE() throws SQLException {
		try(var connection = implementation.createConnection(config)) {

			List<Movie> results = implementation.queryMovies(connection, "Abducted by a Female Alien with Red Eyes");

			Assertions.assertIterableEquals(List.of(ABAFAWRE_2014), results);
			Assertions.assertEquals(ABAFAWRE_2014.actorNames, results.get(0).actorNames, "\"Abducted by a Female Alien with Red Eyes\" did not contain the correct actors");
		}
	}
}
