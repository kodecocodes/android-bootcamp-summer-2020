package codes.jenn.movieapp.repository

import codes.jenn.movieapp.App
import codes.jenn.movieapp.movies.model.Movie

class MovieRepository {
  private val movieDao = App.movieDb.movieDao()

  fun getAllMovies(): List<Movie> = movieDao.fetchMovies()

  fun getMovieById(movieId: Int?) = movieDao.fetchMovieById(movieId)

  fun storeMovies(movies: List<Movie>) = movieDao.storeMovies(movies)

  fun getNumberOfMoviesInDb(): Int = getAllMovies().count()
}