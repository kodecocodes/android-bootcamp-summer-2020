package codes.jenn.movieapp.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import codes.jenn.movieapp.movies.model.Movie
import codes.jenn.movieapp.repository.MovieRepository
import kotlinx.coroutines.launch

class MoviesViewModel(private val movieRepository: MovieRepository) : ViewModel() {

  fun getAllMovies(onMoviesLoaded: (List<Movie>) -> Unit) = viewModelScope.launch {
    val movies = movieRepository.getAllMovies()

    onMoviesLoaded(movies)
  }
}