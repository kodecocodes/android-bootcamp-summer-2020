package codes.jenn.movieapp.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import codes.jenn.movieapp.movies.model.Movie
import codes.jenn.movieapp.repository.MovieRepository
import codes.jenn.movieapp.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class MovieViewModel(
  private val repository: MovieRepository,
  private val userRepository: UserRepository
) : ViewModel() {

  private var currentPage = 1

  fun getMovies(): LiveData<List<Movie>> =
    repository
      .getAllMoviesFlow()
      .flowOn(Dispatchers.IO) // also do extra operations
      .catch {
        emit(emptyList()) // in case of an error, emit an empty list
      }
      .asLiveData() // or repository.getAllMovies()

  fun fetchMovies() {
    viewModelScope.launch {
      repository.loadMoviesForPage(currentPage)
      currentPage++
    }
  }

  fun logOut() {
    userRepository.setUserLoggedIn(false)
  }
}