package codes.jenn.movieapp.moviedetails.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import codes.jenn.movieapp.movies.model.Movie
import codes.jenn.movieapp.repository.MovieRepository
import kotlinx.coroutines.launch

class DetailsViewModel(private val repository: MovieRepository) : ViewModel() {

  private val detailsViewState = MutableLiveData<DetailsViewState>()

  fun getDetailsViewState(): LiveData<DetailsViewState> = detailsViewState

  fun getMovieById(movieId: String) {
    detailsViewState.value = Loading
    viewModelScope.launch {
      val movie = repository.getMovieById(movieId)
      detailsViewState.value = Success(movie)
    }
  }
}

sealed class DetailsViewState
object Loading : DetailsViewState()
data class Success(val movie: Movie?) : DetailsViewState()