package codes.jenn.movieapp

import android.app.Application
import codes.jenn.movieapp.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

  companion object {
    lateinit var instance: App
  }

  override fun onCreate() {
    super.onCreate()
    instance = this
    initKoin()
  }

  private fun initKoin() {
    startKoin {
      androidLogger(Level.DEBUG)
      androidContext(instance.applicationContext)
      modules(
        listOf(
          applicationModule,
          databaseModule,
          presentationModule,
          repositoryModule,
          networkingModule
        )
      )
    }
  }
}