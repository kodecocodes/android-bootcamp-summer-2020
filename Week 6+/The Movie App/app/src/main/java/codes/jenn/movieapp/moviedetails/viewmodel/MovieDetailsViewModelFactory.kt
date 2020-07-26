package codes.jenn.movieapp.moviedetails.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import codes.jenn.movieapp.repository.MovieRepository

class MovieDetailsViewModelFactory(private val repository: MovieRepository) :
  ViewModelProvider.Factory {
  override fun <T : ViewModel?> create(modelClass: Class<T>): T {
    return DetailsViewModel(repository) as T
  }
}