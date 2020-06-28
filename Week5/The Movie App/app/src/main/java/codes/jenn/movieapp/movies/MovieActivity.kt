package codes.jenn.movieapp.movies

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import codes.jenn.movieapp.R
import codes.jenn.movieapp.common.movieList
import codes.jenn.movieapp.login.startLoginActivity
import codes.jenn.movieapp.moviedetails.startMovieDetailsActivity
import codes.jenn.movieapp.movies.list.MovieAdapter
import codes.jenn.movieapp.movies.model.Movie
import codes.jenn.movieapp.repository.MovieRepository
import codes.jenn.movieapp.repository.UserRepository
import kotlinx.android.synthetic.main.activity_main.*

fun startMovieActivity(from: Context) = from.startActivity(Intent(from, MovieActivity::class.java))

class MovieActivity : AppCompatActivity() {

  private val movieRepository by lazy { MovieRepository() }
  private val userRepository by lazy { UserRepository() }
  private val movieAdapter by lazy { MovieAdapter(::movieClicked) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    if (movieRepository.getNumberOfMoviesInDb() < 1) {
      movieRepository.storeMovies(movieList)
    }
    initMovieList()
  }

  private fun initMovieList() {
    moviesRecyclerView.layoutManager = GridLayoutManager(this, 2)
    moviesRecyclerView.adapter = movieAdapter
    movieAdapter.setMovies(movieRepository.getAllMovies())
  }

  private fun movieClicked(movie: Movie) {
    startMovieDetailsActivity(this, movie.id)
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    this.menuInflater.inflate(R.menu.overflow_menu, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    if (item.itemId == R.id.logout) {
      userRepository.setUserLoggedIn(false)
      navigateToLogin()
    }
    return false
  }

  private fun navigateToLogin() {
    startLoginActivity(this)
    finish()
  }
}