package codes.jenn.movieapp.repository

import androidx.lifecycle.LiveData
import codes.jenn.movieapp.movies.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

  fun getAllMovies(): LiveData<List<Movie>>

  fun getAllMoviesFlow(): Flow<List<Movie>>

  suspend fun getMovieById(movieId: String?): Movie

  suspend fun loadMoviesForPage(page: Int)

  suspend fun clearMovies()
}
