package codes.jenn.movieapp.movies.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import codes.jenn.movieapp.common.utils.WorkManagerHelper
import codes.jenn.movieapp.repository.MovieRepository
import codes.jenn.movieapp.repository.UserRepository
import kotlinx.coroutines.launch

class MovieViewModel(
  private val repository: MovieRepository,
  private val userRepository: UserRepository,
  private val workManagerHelper: WorkManagerHelper
) : ViewModel() {

  private var currentPage = 1

  fun getMovies() = repository.getAllMovies()

  fun fetchMovies() {
    viewModelScope.launch {
      repository.loadMoviesForPage(currentPage)
      currentPage++
    }
  }

  fun logOut() {
    userRepository.setUserLoggedIn(false)
  }

  fun setUpSynchronization() {
    workManagerHelper.setUpSynchronization()
  }

  fun stopSynchronization() {
    workManagerHelper.stopSynchronization()
  }
}