package codes.jenn.movieapp.movies.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies")
data class Movie(
  @PrimaryKey
  var id: String,
  var title: String?,
  @SerializedName("overview") var description: String?,
  @SerializedName("vote_count") var numberOfVotes: Int,
  @SerializedName("vote_average") var averageScore: Float,
  @SerializedName("poster_path")
  var image: String?,
  @SerializedName("release_date")
  var releaseDate: String?,
  var popularity: String?
)