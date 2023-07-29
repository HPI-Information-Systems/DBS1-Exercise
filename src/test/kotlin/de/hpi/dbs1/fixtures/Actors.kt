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
    val JACK_BLACK2 = Actor("nm3383009 ", "Jack Black")

    @JvmField
    val JACK_BLACK3 = Actor("nm4795107 ", "Jack Black")

    @JvmField
    val JACK_BLACKBURN = Actor("nm0085601 ", "Jack Blackburn")

    @JvmField
    val JACK_BLACKWELL = Actor("nm12060393", "Jack Blackwell")

    @JvmField
    val GUENTHER_JAUCH = Actor("nm0419393 ", "Günther Jauch")

    @JvmField
    val JOHN_WHITE1 = Actor("nm0924986 ", "John White").apply {
        playedIn.addAll(
            listOf(
                "Argos",
                "Farhope Tower",
                "Hell Hath No Fury",
                "Boy Toy",
                "Fast Food High",
            )
        )
        costarNameToCount.putAll(
            mapOf(
                "April Mullen" to 2,
                "Joe Dinicol" to 2,
                "Alison Pill" to 1,
                "Britt Irvin" to 1,
                "Charlotte Sullivan" to 1,
            )
        )
    }

    @JvmField
    val JOHN_WHITE2 = Actor("nm0924984 ", "John White").apply {
        playedIn.addAll(
            listOf(
                "The Dumb Waiter",
                "Putting on the Agony",
                "Codename: Portcullis"
            )
        )
        costarNameToCount.putAll(
            mapOf(
                "Bill Dean" to 1,
                "Christine Hargreaves" to 1,
                "Clifford Evans" to 1,
                "David Casey" to 1,
                "Frank Crompton" to 1,
            )
        )
    }

    @JvmField
    val JOHN_WHITELEY = Actor("nm0925803 ", "John Whiteley").apply {
        playedIn.addAll(
            listOf(
                "Dark Justice",
                "The American Connection"
            )
        )
        costarNameToCount.putAll(
            mapOf(
                "Brad David" to 1,
                "Danny Keogh" to 1,
                "Peter Lotis" to 1,
                "Richard Cox" to 1,
                "Veronica Wilson" to 1,
            )
        )
    }

    @JvmField
    val JOHN_WHITE3 = Actor("nm1064311 ", "John White").apply {
        playedIn.addAll(
            listOf(
                "Cactuses"
            )
        )
        costarNameToCount.putAll(
            mapOf(
                "Ari Gwasdoff" to 1,
                "Carmencristina Moreno" to 1,
                "Cliff Chamberlain" to 1,
            )
        )
    }

    @JvmField
    val JOHN_WHITE4 = Actor("nm7386568 ", "John White")

    @JvmField
    val JOE_MAMA = Actor("nm13803820", "Joe Mama")

    @JvmField
    val SHIHYUN_WANG = Actor("nm6235717 ", "Shihyun Wang").apply {
        playedIn.addAll(
            listOf(
                "Celebrating Chinese New Year",
                "Cruising with virus on board",
                "Charlie the lonely",
                "Meet the stars",
                "The Virgin Queen with a burning heart"
            )
        )
        costarNameToCount.putAll(
            mapOf(
                "Charles DeNatale" to 1,
                "Chingen Cheng" to 1,
                "Greg Hale" to 1,
                "Joy Dixon" to 1,
                "Ken Peters" to 1
            )
        )
    }

    @JvmField
    val JIMMY_WANG_YU = Actor("nm0911093 ", "Jimmy Wang Yu").apply {
        playedIn.addAll(
            listOf(
                "Soul",
                "The Beheaded 1000",
                "Clash of the Professionals",
                "Shanghai 13",
                "Fantasy Mission Force"
            )
        )
        costarNameToCount.putAll(
            mapOf(
                "Yeh Tien" to 9,
                "Fei Lung" to 7,
                "Paul Chang Chung" to 7,
                "Yi Chang" to 7,
                "Chiao Chiao" to 6,
            )
        )
    }

    @JvmField
    val KUAN_HSIUNG_WANG = Actor("nm0794827 ", "Kuan-Hsiung Wang").apply {
        playedIn.addAll(
            listOf(
                "The Killer from China",
                "Bo sha",
                "Yellow Skin",
                "South Shaolin vs. North Shaolin",
                "Jin fen ye cha"
            )
        )
        costarNameToCount.putAll(
            mapOf(
                "Hui-Shan Yang" to 11,
                "Chun-Hsiung Ko" to 7,
                "Yun Ling" to 6,
                "Hua Tsung" to 5,
                "Sing Chen" to 5
            )
        )
    }

    @JvmField
    val JOEY_WANG = Actor("nm0939153 ", "Joey Wang").apply {
        playedIn.addAll(
            listOf(
                "Shanghai Story",
                "Peony Pavilion",
                "Butterfly and Sword",
                "Chez n' Ham",
                "City Hunter"
            )
        )
        costarNameToCount.putAll(
            mapOf(
                "Jacky Cheung" to 8,
                "Chow Yun-Fat" to 5,
                "Andy Lau" to 4,
                "Tony Chiu-Wai Leung" to 4,
                "Wu Ma" to 4,
            )
        )
    }

    @JvmField
    val YUANLONG_WANG = Actor("nm0911097 ", "Yuanlong Wang").apply {
        playedIn.addAll(
            listOf(
                "Er nu ying xiong chuan",
                "Huang Fei Hong yi guan Cai hong qiao",
                "Pen huo nu lang",
                "Xue sa qing hua",
                "An qi er"
            )
        )
        costarNameToCount.putAll(
            mapOf(
                "Hao Wang" to 9,
                "Li Hua Li" to 9,
                "Kuang-Chao Chiang" to 8,
                "Po Hung" to 8,
                "Naidong Wang" to 6
            )
        )
    }
}
