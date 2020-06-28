package codes.jenn.movieapp

import android.app.Application
import androidx.room.Room
import codes.jenn.movieapp.db.DATABASE_NAME
import codes.jenn.movieapp.db.MovieDatabase

class App : Application() {

  companion object {
    private lateinit var instance: App
    lateinit var movieDb: MovieDatabase

    fun getAppContext() = instance
  }

  override fun onCreate() {
    instance = this
    super.onCreate()
    initRoom()
  }

  private fun initRoom() {
    movieDb = Room.databaseBuilder(this, MovieDatabase::class.java, DATABASE_NAME)
      .allowMainThreadQueries() //This will be fixed in later assignments. This time it is just for simplification
      .build()
  }
}