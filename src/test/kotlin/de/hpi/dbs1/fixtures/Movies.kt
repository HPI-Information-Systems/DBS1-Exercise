package de.hpi.dbs1.fixtures

import de.hpi.dbs1.entities.Movie

object Movies {
    @JvmField
    val WIB_1991 = Movie(
        "tt8659790 ",
        "Willkommen in Berlin",
        1991,
        setOf("Music")
    )

    @JvmField
    val WIB_2009 = Movie(
        "tt22502636",
        "Willkommen in Berlin",
        2009,
        setOf("Documentary")
    )

    @JvmField
    val GITS_1995 = Movie(
        "tt0113568 ",
        "Ghost in the Shell",
        1995,
        setOf("Animation", "Crime", "Action")
    ).apply {
        actorNames.addAll(
            listOf(
                "Akio Ôtsuka",
                "Atsuko Tanaka",
                "Iemasa Kayumi",
                "Kôichi Yamadera",
            )
        )
    }

    @JvmField
    val GITS_TNM_2015 = Movie(
        "tt4337072 ",
        "Ghost in the Shell: The New Movie",
        2015,
        setOf("Animation", "Sci-Fi", "Action")
    ).apply {
        actorNames.addAll(
            listOf(
                "Ikkyu Juku",
                "Kazuya Nakai",
                "Ken'ichirô Matsuda",
                "Maaya Sakamoto",
            )
        )
    }

    @JvmField
    val TIB_2002 = Movie(
        "tt6078244 ",
        "Transformers Interstellar: Blitzkrieg",
        2015,
        setOf("Action", "Adventure", "Short")
    ).apply {
        actorNames.addAll(
            listOf(
                "Felzbug",
                "Samuel Jay Flatman",
                "Zachery Fuller",
            )
        )
    }

    @JvmField
    val TIOP_2014 = Movie(
        "tt6072696 ",
        "Transformers Interstellar: Orion Pax",
        2014,
        setOf("Adventure", "Short")
    ).apply {
        actorNames.addAll(
            listOf(
                "Edward Goodwin",
                "Jared Valkki",
                "Samuel Jay Flatman",
                "Zachery Fuller"
            )
        )
    }

    @JvmField
    val AOT5FCFOS_1998 = Movie(
        "tt0181298 ",
        "Attack of the 50 Foot Chihuahua from Outer Space",
        1998,
        setOf("Comedy", "Short")
    ).apply {
        actorNames.addAll(
            listOf(
                "Kyle Lemke",
            )
        )
    }

    @JvmField
    val AOT5FH_2012 = Movie(
        "tt2582936 ",
        "Attack of the 50 Foot Hero",
        2012,
        setOf("Animation", "Family", "Comedy")
    )

    @JvmField
    val AOT5FK_2011 = Movie(
        "tt3317254 ",
        "Attack of the 50 Foot Kitty!",
        2011,
        setOf("Comedy", "Short")
    ).apply {
        actorNames.addAll(
            listOf(
                "Elyse von Meverden",
                "Indy"
            )
        )
    }

    @JvmField
    val AOT5FMM_1999 = Movie(
        "tt0297749 ",
        "Attack of the 50 Foot Monster Mania",
        1999,
        setOf("Documentary", "Horror")
    ).apply {
        actorNames.addAll(
            listOf(
                "Cassandra Peterson"
            )
        )
    }

    @JvmField
    val AOT5FW_1958 = Movie(
        "tt0051380 ",
        "Attack of the 50 Foot Woman",
        1958,
        setOf("Sci-Fi", "Horror")
    ).apply {
        actorNames.addAll(
            listOf(
                "Allison Hayes",
                "Roy Gordon",
                "William Hudson",
                "Yvette Vickers",
            )
        )
    }

    @JvmField
    val THSO_2011 = Movie(
        "tt2561584 ",
        "9/11 Truth: Hollywood Speaks Out",
        2011,
        setOf("Documentary")
    ).apply {
        actorNames.addAll(
            listOf()
        )
    }

    @JvmField
    val ABAFAWRE_2014 = Movie(
        "tt4018454 ",
        "Abducted by a Female Alien with Red Eyes",
        2014,
        setOf("Horror", "Comedy", "Mystery")
    ).apply {
        actorNames.addAll(
            listOf(
                "Alina Andrisan",
                "Fiorella Sarah Andrisan",
                "Rodrig Andrisan"
            )
        )
    }
}
