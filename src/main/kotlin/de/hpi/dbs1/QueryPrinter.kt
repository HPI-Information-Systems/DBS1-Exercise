package de.hpi.dbs1

import de.hpi.dbs1.entities.Actor
import de.hpi.dbs1.entities.Movie
import java.io.PrintStream

object QueryPrinter {
    fun printMovies(output: PrintStream, movies: Iterable<Movie>) {
        output.println("MOVIES")
        movies.forEach { movie ->
            output.println(
                "${movie.title} (${movie.tConst.trim()}), ${movie.year}, " +
                movie.genres.joinToString(", ")
            )
            if(movie.actorNames.isNotEmpty()) {
                movie.actorNames.forEach { actorName ->
                    output.println("\t$actorName")
                }
            }
        }
    }

    fun printActors(output: PrintStream, actors: Iterable<Actor>) {
        output.println("ACTORS")
        actors.forEach { actor ->
            output.println("${actor.name} (${actor.nConst.trim()})")
            if(actor.playedIn.isNotEmpty()) {
                output.println("\tPLAYED IN")
                actor.playedIn.forEach { movieTitle ->
                    output.println("\t\t$movieTitle")
                }
            }
            if(actor.costarNameToCount.isNotEmpty()) {
                output.println("\tCO-STARS")
                actor.costarNameToCount.forEach { (costarName, sharedMovieCount) ->
                    output.println("\t\t$costarName ($sharedMovieCount)")
                }
            }
        }
    }
}
