package codes.jenn.movieapp.movies.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movie(
  @PrimaryKey
  var id: Int? = null,
  var title: String? = null,
  var summary: String? = null,
  var releaseDate: String? = null,
  val image: Int
)
