package codes.jenn.movieapp.repository

interface UserRepository {

  fun setUserLoggedIn(isLoggedIn: Boolean)

  fun isUserLoggedIn(): Boolean
}