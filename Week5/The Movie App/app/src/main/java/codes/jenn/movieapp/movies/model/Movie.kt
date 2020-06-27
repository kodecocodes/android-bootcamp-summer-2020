package codes.jenn.movieapp.movies.model

import androidx.room.Entity

@Entity
data class Movie(
    var id: Int? = null,
    var title: String? = null,
    var summary: String? = null,
    var releaseDate: String? = null,
    val image: Int
)
