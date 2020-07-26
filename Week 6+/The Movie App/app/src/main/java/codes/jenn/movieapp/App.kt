package codes.jenn.movieapp

import android.app.Application
import androidx.room.Room
import codes.jenn.movieapp.common.utils.CredentialsValidator
import codes.jenn.movieapp.common.utils.Validator
import codes.jenn.movieapp.common.utils.WorkManagerHelper
import codes.jenn.movieapp.db.DATABASE_NAME
import codes.jenn.movieapp.db.MovieDatabase
import codes.jenn.movieapp.login.viewmodel.LoginViewModelFactory
import codes.jenn.movieapp.moviedetails.viewmodel.MovieDetailsViewModelFactory
import codes.jenn.movieapp.movies.viewmodel.MovieViewModelFactory
import codes.jenn.movieapp.networking.buildApiService
import codes.jenn.movieapp.prefs.SharedPrefsManager
import codes.jenn.movieapp.repository.MovieRepository
import codes.jenn.movieapp.repository.MovieRepositoryImpl
import codes.jenn.movieapp.repository.UserRepositoryImpl

class App : Application() {

  companion object {
    private lateinit var instance: App

    private val movieDb: MovieDatabase by lazy {
      Room.databaseBuilder(instance, MovieDatabase::class.java, DATABASE_NAME)
        .build()
    }

    private val movieDao by lazy { movieDb.movieDao() }
    private val movieApiService by lazy { buildApiService() }
    private val credentialsValidator: Validator by lazy { CredentialsValidator() }
    val userRepository by lazy { UserRepositoryImpl(SharedPrefsManager()) }

    val repository: MovieRepository by lazy { MovieRepositoryImpl(movieDao, movieApiService) }
    val movieViewModelFactory by lazy {
      MovieViewModelFactory(
        repository,
        userRepository,
        workManagerHelper
      )
    }
    val loginViewModelFactory by lazy {
      LoginViewModelFactory(
        userRepository,
        credentialsValidator
      )
    }
    val movieDetailsViewModelFactory by lazy { MovieDetailsViewModelFactory(repository) }
    private val workManagerHelper by lazy { WorkManagerHelper(instance) }

    fun getAppContext() = instance
  }

  override fun onCreate() {
    super.onCreate()
    instance = this
  }
}