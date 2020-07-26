package codes.jenn.movieapp.common.utils

interface Validator {

  fun setCredentials(username: String, password: String)

  fun isUsernameValid(): Boolean

  fun isPasswordValid(): Boolean

  fun areCredentialsValid(): Boolean
}