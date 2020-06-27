package codes.jenn.movieapp.movies

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import codes.jenn.movieapp.R
import codes.jenn.movieapp.movieList
import codes.jenn.movieapp.movies.list.MovieAdapter
import codes.jenn.movieapp.movies.model.Movie
import kotlinx.android.synthetic.main.activity_main.*

fun startMovieActivity(from: Context) = from.startActivity(Intent(from, MovieActivity::class.java))

class MovieActivity : AppCompatActivity() {

  private val movieAdapter by lazy { MovieAdapter(::movieClicked) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    initMovieList()
  }

  private fun initMovieList() {
    moviesRecyclerView.layoutManager = GridLayoutManager(this, 2)
    moviesRecyclerView.adapter = movieAdapter
    movieAdapter.setMovies(movieList)
  }

  private fun movieClicked(movie: Movie) {
    //do something with the movie
  }
}