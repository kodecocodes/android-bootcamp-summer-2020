package codes.jenn.movieapp.repository

import codes.jenn.movieapp.prefs.SharedPrefsManager

class UserRepository(private val sharedPrefsManager: SharedPrefsManager) {


  fun setUserLoggedIn(isLoggedIn: Boolean) = sharedPrefsManager.setUserLoggedIn(isLoggedIn)

  fun isUserLoggedIn() = sharedPrefsManager.isUserLoggedIn()
}