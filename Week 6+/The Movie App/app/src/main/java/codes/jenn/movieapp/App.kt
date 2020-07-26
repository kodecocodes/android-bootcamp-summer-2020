package codes.jenn.movieapp

import android.app.Application
import androidx.room.Room
import codes.jenn.movieapp.db.DATABASE_NAME
import codes.jenn.movieapp.db.MovieDatabase
import codes.jenn.movieapp.movies.viewmodel.MovieViewModelFactory
import codes.jenn.movieapp.networking.buildApiService
import codes.jenn.movieapp.prefs.SharedPrefsManager
import codes.jenn.movieapp.repository.MovieRepository
import codes.jenn.movieapp.repository.MovieRepositoryImpl
import codes.jenn.movieapp.repository.UserRepository

class App : Application() {

  companion object {
    private lateinit var instance: App

    private val movieDb: MovieDatabase by lazy {
      Room.databaseBuilder(instance, MovieDatabase::class.java, DATABASE_NAME)
        .build()
    }

    private val movieDao by lazy { movieDb.movieDao() }
    private val movieApiService by lazy { buildApiService() }
    val userRepository by lazy { UserRepository(SharedPrefsManager()) }

    val repository: MovieRepository by lazy { MovieRepositoryImpl(movieDao, movieApiService) }
    val viewModelFactory by lazy {
      MovieViewModelFactory(
        repository,
        userRepository
      )
    }

    fun getAppContext() = instance
  }

  override fun onCreate() {
    super.onCreate()
    instance = this
  }
}