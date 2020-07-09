package codes.jenn.movieapp.repository

import codes.jenn.movieapp.App
import codes.jenn.movieapp.movies.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepository {
  private val movieDao = App.movieDb.movieDao()

  suspend fun getAllMovies(): List<Movie> = movieDao.fetchMovies()

  suspend fun getMovieById(movieId: Int?) = movieDao.fetchMovieById(movieId)

  private suspend fun storeMovies(movies: List<Movie>) = movieDao.storeMovies(movies)

  suspend fun storeMoviesIfNotEmpty(movies: List<Movie>) {
    if (getAllMovies().count() < 1) storeMovies(movies)
  }
}