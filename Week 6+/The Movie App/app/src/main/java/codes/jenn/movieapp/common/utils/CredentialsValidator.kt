package codes.jenn.movieapp.common.utils

class CredentialsValidator : Validator {
  private lateinit var username: String
  private lateinit var password: String

  override fun setCredentials(username: String, password: String) {
    this.username = username
    this.password = password
  }

  override fun isUsernameValid() = username.length > 2

  override fun isPasswordValid() = password.length >= 6

  override fun areCredentialsValid() = isUsernameValid() && isPasswordValid()
}