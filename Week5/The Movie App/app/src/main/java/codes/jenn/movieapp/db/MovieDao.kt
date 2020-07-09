package codes.jenn.movieapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import codes.jenn.movieapp.movies.model.Movie

@Dao
interface MovieDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun storeMovies(movies: List<Movie>)

  @Query("SELECT * FROM Movie")
  suspend fun fetchMovies(): List<Movie>

  @Query("SELECT * FROM Movie WHERE id =:movieId")
  suspend fun fetchMovieById(movieId: Int?): Movie
}