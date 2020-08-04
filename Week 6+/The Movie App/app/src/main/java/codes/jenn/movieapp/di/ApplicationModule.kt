package codes.jenn.movieapp.di

import android.content.Context
import androidx.work.WorkManager
import codes.jenn.movieapp.common.utils.CredentialsValidator
import codes.jenn.movieapp.common.utils.Validator
import codes.jenn.movieapp.common.utils.WorkManagerHelper
import codes.jenn.movieapp.login.view.LoginActivity
import codes.jenn.movieapp.prefs.SharedPrefsManager
import codes.jenn.movieapp.worker.SynchronizeMoviesWorker
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val applicationModule = module {
  single {
    SharedPrefsManager(
      get(),
      androidContext().getSharedPreferences(MOVIES_SHARED_PREFS, Context.MODE_PRIVATE)
    )
  }

  single { WorkManager.getInstance(get()) }

  single { WorkManagerHelper(get()) }

  single { SynchronizeMoviesWorker(androidContext(), get(), get()) }

  scope<LoginActivity> {
    scoped<Validator> { CredentialsValidator() }
  }
//  factory<Validator> { CredentialsValidator() }
}

private const val MOVIES_SHARED_PREFS = ""