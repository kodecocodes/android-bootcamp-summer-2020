package codes.jenn.movieapp.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import codes.jenn.movieapp.common.utils.CredentialsValidator
import codes.jenn.movieapp.repository.UserRepository

class LoginViewModel(
  private val credentialsValidator: CredentialsValidator,
  private val userRepository: UserRepository
) : ViewModel() {

  private val loginViewState = MutableLiveData<LoginViewState>()

  fun getLoginViewState(): LiveData<LoginViewState> = loginViewState

  fun checkIfUserLoggedIn() {
    if (userRepository.isUserLoggedIn()) {
      loginViewState.value = UserLoggedIn
    }
  }

  fun checkCredentials(username: String, password: String) {
    credentialsValidator.setCredentials(username, password)
    checkUsername()
    checkPassword()
    if (credentialsValidator.areCredentialsValid()) {
      userRepository.setUserLoggedIn(true)
      loginViewState.value = UserLoggedIn
    }
  }

  private fun checkUsername() {
    if (!credentialsValidator.isUsernameValid()) {
      loginViewState.value = InvalidUsername
    } else {
      loginViewState.value = ValidUsername
    }
  }

  private fun checkPassword() {
    if (!credentialsValidator.isPasswordValid()) {
      loginViewState.value = InvalidPassword
    } else {
      loginViewState.value = ValidPassword
    }
  }
}

sealed class LoginViewState
object UserLoggedIn : LoginViewState()
object InvalidUsername : LoginViewState()
object InvalidPassword : LoginViewState()
object ValidUsername : LoginViewState()
object ValidPassword : LoginViewState()