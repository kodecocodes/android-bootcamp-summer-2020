package codes.jenn.movieapp.prefs

import android.content.Context
import android.content.SharedPreferences

class SharedPrefsManager(private val context: Context, private val prefs: SharedPreferences) {

  fun setUserLoggedIn(isLoggedIn: Boolean) {
    prefs.edit().putBoolean(KEY_LOGGED_IN, isLoggedIn).apply()
  }

  fun isUserLoggedIn() = prefs.getBoolean(KEY_LOGGED_IN, false)
}

private const val KEY_LOGGED_IN = ""