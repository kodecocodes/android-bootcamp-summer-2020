package codes.jenn.movieapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import codes.jenn.movieapp.movies.model.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
}

const val DATABASE_NAME = "movie_database"