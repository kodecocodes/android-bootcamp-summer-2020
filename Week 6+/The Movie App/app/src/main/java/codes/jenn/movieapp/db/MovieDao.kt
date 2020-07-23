package codes.jenn.movieapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import codes.jenn.movieapp.movies.model.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun storeMovies(movies: List<Movie>)

  @Query("SELECT * FROM movies")
  fun fetchMovies(): LiveData<List<Movie>>

  @Query("SELECT * FROM movies")
  fun fetchMoviesFlow(): Flow<List<Movie>>

  @Query("SELECT * FROM movies WHERE id =:movieId")
  suspend fun fetchMovieById(movieId: Int?): Movie

  @Query("DELETE FROM movies")
  suspend fun clearMovies()
}