package de.hpi.dbs1;

import de.hpi.dbs1.entities.Actor;
import org.junit.jupiter.api.*;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

import static de.hpi.dbs1.fixtures.Actors.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class JDBCExerciseTutorActorTests {

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
	@Order(3)
	@DisplayName("Actor query: Günther Jauch")
	void testQueryActorsGJ() throws SQLException {
		try(var connection = implementation.createConnection(config)) {
			List<Actor> results = implementation.queryActors(connection, "ther Jauch");
			Assertions.assertEquals(List.of(GUENTHER_JAUCH), results);
		}
	}

	@Test
	@Order(3)
	@DisplayName("Actor query: John White")
	void testQueryActorsJW() throws SQLException {
		try(var connection = implementation.createConnection(config)) {
			List<Actor> results = implementation.queryActors(connection, "John White");
			Assertions.assertEquals(List.of(JOHN_WHITE1, JOHN_WHITE2, JOHN_WHITELEY, JOHN_WHITE3, JOHN_WHITE4), results);

			Assertions.assertEquals(JOHN_WHITE1, results.get(0));
			Assertions.assertEquals(JOHN_WHITE1.playedIn, results.get(0).playedIn);
			Assertions.assertEquals(JOHN_WHITE1.costarNameToCount, results.get(0).costarNameToCount);
		}
	}

	@Test
	@Order(3)
	@DisplayName("Actor query: Joe Mama")
	void testQueryActorsJM() throws SQLException {
		try(var connection = implementation.createConnection(config)) {
			List<Actor> results = implementation.queryActors(connection, "Joe Mama");
			Assertions.assertEquals(List.of(JOE_MAMA), results);

			Assertions.assertEquals(JOE_MAMA, results.get(0));
			Assertions.assertEquals(JOE_MAMA.playedIn, results.get(0).playedIn);
			Assertions.assertEquals(JOE_MAMA.costarNameToCount, results.get(0).costarNameToCount);
		}
	}

	@Test
	@Order(3)
	@DisplayName("Actor query: Wang")
	void testQueryActorsW() throws SQLException {

		try(var connection = implementation.createConnection(config)) {
			List<Actor> results = implementation.queryActors(connection, "Wang");
			Assertions.assertEquals(List.of(SHIHYUN_WANG, JIMMY_WANG_YU, KUAN_HSIUNG_WANG, JOEY_WANG, YUANLONG_WANG), results);

			Assertions.assertEquals(SHIHYUN_WANG, results.get(0));
			Assertions.assertEquals(SHIHYUN_WANG.playedIn, results.get(0).playedIn);
			Assertions.assertEquals(SHIHYUN_WANG.costarNameToCount, results.get(0).costarNameToCount);
		}
	}
}
