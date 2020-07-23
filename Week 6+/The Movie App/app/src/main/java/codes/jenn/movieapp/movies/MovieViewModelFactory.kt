package codes.jenn.movieapp.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import codes.jenn.movieapp.repository.MovieRepository
import codes.jenn.movieapp.repository.UserRepository

class MovieViewModelFactory(
  private val repository: MovieRepository,
  private val userRepository: UserRepository
) : ViewModelProvider.Factory {

  override fun <T : ViewModel?> create(modelClass: Class<T>): T {
    return MovieViewModel(repository, userRepository) as T
  }
}