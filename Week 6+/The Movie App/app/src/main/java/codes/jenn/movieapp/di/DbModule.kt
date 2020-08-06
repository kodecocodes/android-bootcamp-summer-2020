package codes.jenn.movieapp.di

import androidx.room.Room
import codes.jenn.movieapp.db.DATABASE_NAME
import codes.jenn.movieapp.db.MovieDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {

  single {
    Room.databaseBuilder(androidContext(), MovieDatabase::class.java, DATABASE_NAME).build()
  }
  single { get<MovieDatabase>().movieDao() }
}