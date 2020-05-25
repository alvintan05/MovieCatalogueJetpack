package com.alvin.moviecataloguejetpack.utils

import com.alvin.moviecataloguejetpack.data.source.local.DetailMovieEntity
import com.alvin.moviecataloguejetpack.data.source.local.MovieEntity
import com.alvin.moviecataloguejetpack.data.source.remote.response.*

object DataDummy {

    fun generateRemoteDummyMovies(page: Int): List<Movie> {
        val movies = ArrayList<Movie>()

        movies.add(
            Movie(
                419704,
                "Ad Astra",
                6.0,
                "The near future, a time when both hope and hardships drive humanity to look to the stars and beyond. While a mysterious phenomenon menaces to destroy life on planet Earth, astronaut Roy McBride undertakes a mission across the immensity of space and its many perils to uncover the truth about a lost expedition that decades before boldly faced emptiness and silence in search of the unknown.",
                "/xBHvZcjRiWyobQ9kxBhO6B2dtRI.jpg"
            )
        )

        movies.add(
            Movie(
                385103,
                "Scoob!",
                8.1,
                "In Scooby-Doo’s greatest adventure yet, see the never-before told story of how lifelong friends Scooby and Shaggy first met and how they joined forces with young detectives Fred, Velma, and Daphne to form the famous Mystery Inc. Now, with hundreds of cases solved, Scooby and the gang face their biggest, toughest mystery ever: an evil plot to unleash the ghost dog Cerberus upon the world. As they race to stop this global “dogpocalypse,” the gang discovers that Scooby has a secret legacy and an epic destiny greater than anyone ever imagined.",
                "/zG2l9Svw4PTldWJAzC171Y3d6G8.jpg"
            )
        )
        return movies
    }

    fun generateRemoteDummyMovieDetail(movieId: Int): DetailMovieResponse {

        val genre = listOf(
            Genre(
                878,
                "Science Fiction"
            )
        )

        return DetailMovieResponse(
            419704,
            "Ad Astra",
            "2019-09-17",
            123,
            6.0,
            genre,
            "In Scooby-Doo’s greatest adventure yet, see the never-before told story of how lifelong friends Scooby and Shaggy first met and how they joined forces with young detectives Fred, Velma, and Daphne to form the famous Mystery Inc. Now, with hundreds of cases solved, Scooby and the gang face their biggest, toughest mystery ever: an evil plot to unleash the ghost dog Cerberus upon the world. As they race to stop this global “dogpocalypse,” the gang discovers that Scooby has a secret legacy and an epic destiny greater than anyone ever imagined.",
            "/zG2l9Svw4PTldWJAzC171Y3d6G8.jpg",
            "/5BwqwxMEjeFtdknRV792Svo0K1v.jpg"
        )
    }

    fun generateRemoteDummyTvShows(page: Int): List<TvShow> {
        val movies = ArrayList<TvShow>()

        movies.add(
            TvShow(
                60735,
                "The Flash",
                7.3,
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \\\"meta-human\\\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                "/wHa6KOJAoNTFLFtp7wguUJKSnju.jpg"
            )
        )

        movies.add(
            TvShow(
                2734,
                "Law & Order: Special Victims Unit",
                7.0,
                "In the criminal justice system, sexually-based offenses are considered especially heinous. In New York City, the dedicated detectives who investigate these vicious felonies are members of an elite squad known as the Special Victims Unit. These are their stories.",
                "/6t6r1VGQTTQecN4V0sZeqsmdU9g.jpg"
            )
        )
        return movies
    }

    fun generateRemoteDummyTvShowDetail(movieId: Int): DetailTvShowResponse {
        val genre = listOf(
            Genre(
                18,
                "Drama"
            )
        )

        val runtime = listOf(44)

        return DetailTvShowResponse(
            60735,
            "The Flash",
            "2014-10-07",
            runtime,
            7.3,
            genre,
            "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \\\"meta-human\\\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
            "/wHa6KOJAoNTFLFtp7wguUJKSnju.jpg",
            "/jC1KqsFx8ZyqJyQa2Ohi7xgL7XC.jpg"
        )
    }

    fun generateDummyMovies(page: Int): List<MovieEntity> {
        val movies = ArrayList<MovieEntity>()

        movies.add(
            MovieEntity(
                419704,
                "Ad Astra",
                6.0,
                "The near future, a time when both hope and hardships drive humanity to look to the stars and beyond. While a mysterious phenomenon menaces to destroy life on planet Earth, astronaut Roy McBride undertakes a mission across the immensity of space and its many perils to uncover the truth about a lost expedition that decades before boldly faced emptiness and silence in search of the unknown.",
                "/xBHvZcjRiWyobQ9kxBhO6B2dtRI.jpg"
            )
        )

        movies.add(
            MovieEntity(
                385103,
                "Scoob!",
                8.1,
                "In Scooby-Doo’s greatest adventure yet, see the never-before told story of how lifelong friends Scooby and Shaggy first met and how they joined forces with young detectives Fred, Velma, and Daphne to form the famous Mystery Inc. Now, with hundreds of cases solved, Scooby and the gang face their biggest, toughest mystery ever: an evil plot to unleash the ghost dog Cerberus upon the world. As they race to stop this global “dogpocalypse,” the gang discovers that Scooby has a secret legacy and an epic destiny greater than anyone ever imagined.",
                "/zG2l9Svw4PTldWJAzC171Y3d6G8.jpg"
            )
        )
        return movies
    }

    fun generateDummyMovieDetail(movieId: Int): DetailMovieEntity {
        return DetailMovieEntity(
            419704,
            "Ad Astra",
            "2019-09-17",
            123,
            6.0,
            "Science Fiction",
            "In Scooby-Doo’s greatest adventure yet, see the never-before told story of how lifelong friends Scooby and Shaggy first met and how they joined forces with young detectives Fred, Velma, and Daphne to form the famous Mystery Inc. Now, with hundreds of cases solved, Scooby and the gang face their biggest, toughest mystery ever: an evil plot to unleash the ghost dog Cerberus upon the world. As they race to stop this global “dogpocalypse,” the gang discovers that Scooby has a secret legacy and an epic destiny greater than anyone ever imagined.",
            "/zG2l9Svw4PTldWJAzC171Y3d6G8.jpg",
            "/5BwqwxMEjeFtdknRV792Svo0K1v.jpg"
        )
    }

    fun generateDummyTvShows(page: Int): List<MovieEntity> {
        val movies = ArrayList<MovieEntity>()

        movies.add(
            MovieEntity(
                60735,
                "The Flash",
                7.3,
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \\\"meta-human\\\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                "/wHa6KOJAoNTFLFtp7wguUJKSnju.jpg"
            )
        )

        movies.add(
            MovieEntity(
                2734,
                "Law & Order: Special Victims Unit",
                7.0,
                "In the criminal justice system, sexually-based offenses are considered especially heinous. In New York City, the dedicated detectives who investigate these vicious felonies are members of an elite squad known as the Special Victims Unit. These are their stories.",
                "/6t6r1VGQTTQecN4V0sZeqsmdU9g.jpg"
            )
        )
        return movies
    }

    fun generateDummyTvShowDetail(movieId: Int): DetailMovieEntity {
        return DetailMovieEntity(
            60735,
            "The Flash",
            "2014-10-07",
            44,
            7.3,
            "Drama",
            "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \\\"meta-human\\\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
            "/wHa6KOJAoNTFLFtp7wguUJKSnju.jpg",
            "/jC1KqsFx8ZyqJyQa2Ohi7xgL7XC.jpg"
        )
    }

}