import de.hpi.dbs1.ChosenImplementation
import de.hpi.dbs1.ConnectionConfig
import de.hpi.dbs1.JDBCExercise
import de.hpi.dbs1.entities.Actor
import de.hpi.dbs1.entities.Movie
import java.sql.Connection
import java.util.logging.Logger

@ChosenImplementation(false)
class JDBCExerciseKotlinImplementation : JDBCExercise {

    val logger = Logger.getLogger(javaClass.simpleName)

    override fun createConnection(config: ConnectionConfig): Connection {
        TODO("Not yet implemented")
    }

    override fun queryMovies(
        connection: Connection,
        keywords: String
    ): List<Movie> {
        logger.info(keywords)
        val movies = ArrayList<Movie>()

        /*
        val myMovie = Movie("??????????", "My Movie", 2023, setOf("Indie"))
        myMovie.actorNames.add("Myself")
        movies.add(myMovie)
        */

        TODO("Not yet implemented")
    }

    override fun queryActors(
        connection: Connection,
        keywords: String
    ): List<Actor> {
        logger.info(keywords)
        val actors = ArrayList<Actor>()

        TODO("Not yet implemented")
    }
}
