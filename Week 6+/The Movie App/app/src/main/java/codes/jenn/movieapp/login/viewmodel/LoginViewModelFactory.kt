package codes.jenn.movieapp.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import codes.jenn.movieapp.common.utils.CredentialsValidator
import codes.jenn.movieapp.repository.UserRepository

class LoginViewModelFactory(
  private val userRepository: UserRepository,
  private val credentialsValidator: CredentialsValidator
) : ViewModelProvider.Factory {
  override fun <T : ViewModel?> create(modelClass: Class<T>): T {
    return LoginViewModel(credentialsValidator, userRepository) as T
  }
}