package codes.jenn.movieapp.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import codes.jenn.movieapp.repository.MovieRepository

class MoviesViewModelFactory(private val movieRepository: MovieRepository) :
  ViewModelProvider.Factory {

  override fun <T : ViewModel?> create(modelClass: Class<T>): T {
      return MoviesViewModel(movieRepository) as T
  }
}