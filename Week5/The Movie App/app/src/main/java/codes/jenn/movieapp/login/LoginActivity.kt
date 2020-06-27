package codes.jenn.movieapp.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import codes.jenn.movieapp.R
import codes.jenn.movieapp.common.extensions.onClick
import codes.jenn.movieapp.common.utils.CredentialsValidator
import codes.jenn.movieapp.movies.startMovieActivity
import codes.jenn.movieapp.prefs.SharedPrefsManager
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

  private val credentialsValidator by lazy { CredentialsValidator() }
  private val sharedPrefs by lazy { SharedPrefsManager(this) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_login)
    checkIfUserLoggedIn()
    loginUser.onClick { checkCredentials() }
  }

  private fun checkIfUserLoggedIn() {
    if (sharedPrefs.isUserLoggedIn()) {
      navigateToMovieScreen()
    }
  }

  private fun checkCredentials() {
    credentialsValidator.setCredentials(
      usernameInput.text.toString(),
      passwordInput.text.toString()
    )

    toggleUsernameState()
    togglePasswordState()

    if (credentialsValidator.areCredentialsValid()) {
      sharedPrefs.setUserLoggedIn(true)
      navigateToMovieScreen()
    }
  }

  private fun toggleUsernameState() {
    if (!credentialsValidator.isUsernameValid()) {
      usernameInputLayout.error = getString(R.string.username_error)
    } else {
      usernameInputLayout.error = null
    }
  }

  private fun togglePasswordState() {
    if (!credentialsValidator.isPasswordValid()) {
      passwordInputLayout.error = getString(R.string.password_error)
    } else {
      passwordInputLayout.error = null
    }
  }

  private fun navigateToMovieScreen() {
    startMovieActivity(this)
    finish()
  }
}