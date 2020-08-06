package codes.jenn.movieapp.login.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import codes.jenn.movieapp.R
import codes.jenn.movieapp.common.extensions.onClick
import codes.jenn.movieapp.common.extensions.subscribe
import codes.jenn.movieapp.login.viewmodel.*
import codes.jenn.movieapp.movies.view.startMovieActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.androidx.scope.lifecycleScope

fun startLoginActivity(from: Context) = from.startActivity(Intent(from, LoginActivity::class.java))

class LoginActivity : AppCompatActivity() {

  private val viewModel: LoginViewModel by lifecycleScope.inject()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_login)
    setClickListeners()
    subscribeToData()
    viewModel.checkIfUserLoggedIn()
  }

  private fun setClickListeners() {
    loginUser.onClick {
      viewModel.checkCredentials(
        usernameInput.text.toString(),
        passwordInput.text.toString()
      )
    }
  }

  private fun subscribeToData() {
    viewModel.getLoginViewState().subscribe(this, ::onLoginViewStateChanged)
  }

  private fun onLoginViewStateChanged(loginViewState: LoginViewState) = when (loginViewState) {
    UserLoggedIn -> navigateToMovieScreen()
    InvalidUsername -> setUsernameError()
    InvalidPassword -> setPasswordError()
    ValidUsername -> removeUsernameError()
    ValidPassword -> removePasswordError()
  }

  private fun navigateToMovieScreen() {
    startMovieActivity(this)
    finish()
  }

  private fun setUsernameError() {
    usernameInputLayout.error = getString(R.string.username_error)
  }

  private fun setPasswordError() {
    passwordInputLayout.error = getString(R.string.password_error)
  }

  private fun removeUsernameError() {
    usernameInputLayout.error = null
  }

  private fun removePasswordError() {
    passwordInputLayout.error = null
  }

  override fun onDestroy() {
    lifecycleScope.close()
    super.onDestroy()
  }
}