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
public class JDBCExerciseImplementationTests {

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
	@Order(1)
	@DisplayName("connectDatabase(config) should return a valid connection to the Postgres server")
	void testConnectDatabase() throws SQLException {
		try(var connection = implementation.createConnection(config)) {
			Assertions.assertTrue(connection.isValid(0), "connection is not valid");
		}
	}

	@Test
	@Order(2)
	@DisplayName("Movie query 1: Willkommen in Berlin")
	void testQueryMovies1() throws SQLException {
		try(var connection = implementation.createConnection(config)) {
			List<Movie> results = implementation.queryMovies(connection, "Willkommen in Berlin");
			Assertions.assertIterableEquals(List.of(WIB_1991, WIB_2009), results);
			results.forEach(movie -> Assertions.assertEquals(0, movie.actorNames.size()));
		}
	}

	@Test
	@Order(2)
	@DisplayName("Movie query 2: Ghost in the Shell")
	void testQueryMovies2() throws SQLException {
		try(var connection = implementation.createConnection(config)) {
			List<Movie> results = implementation.queryMovies(connection, "Ghost in the Shell");

			Assertions.assertEquals(GITS_1995, results.get(0), "The first result should be \"Ghost in the Shell\" from 1995");
			Assertions.assertEquals(GITS_1995.actorNames, results.get(0).actorNames, "\"Ghost in the Shell (1995)\" did not contain the correct actors");
			Assertions.assertTrue(results.contains(GITS_TNM_2015), "The result should contain \"Ghost in the Shell: The New Movie\" from 2015");
		}
	}

	@Test
	@Order(3)
	@DisplayName("Actor query 1: Tom Cruise")
	void testQueryActors1() throws SQLException {
		try(var connection = implementation.createConnection(config)) {
			List<Actor> results = implementation.queryActors(connection, "Tom Cruise");
			Assertions.assertEquals(1, results.size());

			Assertions.assertEquals(TOM_CRUISE, results.get(0));
			Assertions.assertEquals(TOM_CRUISE.playedIn, results.get(0).playedIn);
			Assertions.assertEquals(TOM_CRUISE.costarNameToCount, results.get(0).costarNameToCount);
		}
	}

	@Test
	@Order(3)
	@DisplayName("Actor query 2: Jack Black")
	void testQueryActors2() throws SQLException {
		try(var connection = implementation.createConnection(config)) {
			List<Actor> results = implementation.queryActors(connection, "Jack Black");
			Assertions.assertEquals(List.of(JACK_BLACK, JACK_BLACKBURN, JACK_BLACKWELL), results);

			Assertions.assertEquals(JACK_BLACK, results.get(0));
			Assertions.assertEquals(JACK_BLACK.playedIn, results.get(0).playedIn);
			Assertions.assertEquals(JACK_BLACK.costarNameToCount, results.get(0).costarNameToCount);
		}
	}
}
