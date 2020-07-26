package codes.jenn.movieapp.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

  private val loginViewState = MutableLiveData<LoginViewState>()

  fun getLoginViewState(): LiveData<LoginViewState> = loginViewState
}

sealed class LoginViewState