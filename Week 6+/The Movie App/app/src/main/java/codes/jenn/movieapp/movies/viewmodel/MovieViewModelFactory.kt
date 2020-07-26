package codes.jenn.movieapp.movies.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import codes.jenn.movieapp.common.utils.WorkManagerHelper
import codes.jenn.movieapp.repository.MovieRepository
import codes.jenn.movieapp.repository.UserRepository

class MovieViewModelFactory(
  private val repository: MovieRepository,
  private val userRepository: UserRepository,
  private val workManagerHelper: WorkManagerHelper
) : ViewModelProvider.Factory {

  override fun <T : ViewModel?> create(modelClass: Class<T>): T {
    return MovieViewModel(
      repository,
      userRepository,
      workManagerHelper
    ) as T
  }
}