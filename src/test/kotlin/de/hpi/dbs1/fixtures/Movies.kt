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
}
