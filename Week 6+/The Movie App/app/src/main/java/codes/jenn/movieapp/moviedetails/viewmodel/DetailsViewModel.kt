package codes.jenn.movieapp.moviedetails.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DetailsViewModel : ViewModel() {

  private val detailsViewState = MutableLiveData<DetailsViewState>()

  fun getDetailsViewState(): LiveData<DetailsViewState> = detailsViewState
}

sealed class DetailsViewState