package codes.jenn.movieapp.repository

import androidx.lifecycle.LiveData
import codes.jenn.movieapp.db.MovieDao
import codes.jenn.movieapp.movies.model.Movie
import codes.jenn.movieapp.networking.MovieApiService
import codes.jenn.movieapp.networking.getMoviesQuery
import kotlinx.coroutines.flow.Flow

class MovieRepositoryImpl(
  private val movieDao: MovieDao,
  private val movieApiService: MovieApiService
) : MovieRepository {

  override fun getAllMovies(): LiveData<List<Movie>> = movieDao.fetchMovies()

  override fun getAllMoviesFlow(): Flow<List<Movie>> = movieDao.fetchMoviesFlow()

  override suspend fun getMovieById(movieId: Int?): Movie = movieDao.fetchMovieById(movieId)

  override suspend fun loadMoviesForPage(page: Int) {
    val moviesList = movieApiService.getPopularMovies(getMoviesQuery(page))

    storeMoviesIfNotEmpty(moviesList.movies)
  }

  private suspend fun storeMoviesIfNotEmpty(movies: List<Movie>) {
    if (movies.isNotEmpty()) {
      movieDao.storeMovies(movies)
    }
  }

  override suspend fun clearMovies() {
    movieDao.clearMovies()
  }
}