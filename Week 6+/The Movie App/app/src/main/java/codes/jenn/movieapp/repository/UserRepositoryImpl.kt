package codes.jenn.movieapp.repository

import codes.jenn.movieapp.prefs.SharedPrefsManager

class UserRepositoryImpl(private val sharedPrefsManager: SharedPrefsManager) : UserRepository {


  override fun setUserLoggedIn(isLoggedIn: Boolean) = sharedPrefsManager.setUserLoggedIn(isLoggedIn)

  override fun isUserLoggedIn() = sharedPrefsManager.isUserLoggedIn()
}