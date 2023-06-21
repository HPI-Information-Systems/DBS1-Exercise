package de.hpi.dbs1.fixtures

import de.hpi.dbs1.entities.Actor

object Actors {
    @JvmField
    val TOM_CRUISE = Actor("nm0000129 ", "Tom Cruise").apply {
        playedIn.addAll(
            listOf(
                "Mission: Impossible - Dead Reckoning - Part Two",
                "Mission: Impossible - Dead Reckoning - Part One",
                "Top Gun: Maverick",
                "Au Revoir, Chris Hemsworth",
                "Mission: Impossible - Fallout",
            )
        )
        costarNameToCount.putAll(
            mapOf(
                "Nicole Kidman" to 4,
                "Rebecca Ferguson" to 4,
                "Simon Pegg" to 4,
                "Ving Rhames" to 3,
                "Cameron Diaz" to 2,
            )
        )
    }

    @JvmField
    val JACK_BLACK = Actor("nm0085312 ", "Jack Black").apply {
        playedIn.addAll(
            listOf(
                "Kung Fu Panda 4",
                "The Super Mario Bros. Movie",
                "Apollo 10½: A Space Age Childhood",
                "Jumanji: The Next Level",
                "Don't Worry, He Won't Get Far on Foot",
            )
        )
        costarNameToCount.putAll(
            mapOf(
                "Angelina Jolie" to 5,
                "Dustin Hoffman" to 5,
                "Amanda Peet" to 2,
                "Ben Stiller" to 2,
                "Dwayne Johnson" to 2,
            )
        )
    }

    @JvmField
    val JACK_BLACKBURN = Actor("nm0085601 ", "Jack Blackburn")
    @JvmField
    val JACK_BLACKWELL = Actor("nm12060393", "Jack Blackwell")
}
