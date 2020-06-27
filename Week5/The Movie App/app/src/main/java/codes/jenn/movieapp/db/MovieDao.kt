package codes.jenn.movieapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import codes.jenn.movieapp.movies.model.Movie

@Dao
interface MovieDao {

    @Insert
    fun storeMovies(movies: List<Movie>)

    @Query("SELECT * FROM Movie")
    fun fetchMovies(): List<Movie>
}