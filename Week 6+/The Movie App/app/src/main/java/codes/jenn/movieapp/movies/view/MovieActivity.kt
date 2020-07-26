package codes.jenn.movieapp.movies.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.work.WorkManager
import codes.jenn.movieapp.App
import codes.jenn.movieapp.R
import codes.jenn.movieapp.common.extensions.subscribe
import codes.jenn.movieapp.login.view.startLoginActivity
import codes.jenn.movieapp.moviedetails.view.startMovieDetailsActivity
import codes.jenn.movieapp.movies.list.LazyLoadingListener
import codes.jenn.movieapp.movies.list.MovieAdapter
import codes.jenn.movieapp.movies.model.Movie
import codes.jenn.movieapp.movies.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.activity_main.*

fun startMovieActivity(from: Context) = from.startActivity(Intent(from, MovieActivity::class.java))

class MovieActivity : AppCompatActivity() {

  private val viewModel by viewModels<MovieViewModel> { App.movieViewModelFactory }
  private val movieAdapter by lazy { MovieAdapter(::movieClicked) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    initMovieList()
    viewModel.fetchMovies()
    viewModel.setUpSynchronization()
    subscribeToData()
  }

  private fun subscribeToData() {
    viewModel.getMovies().subscribe(this, ::onMoviesReceived)
  }

  private fun onMoviesReceived(movies: List<Movie>) {
    movieAdapter.setMovies(movies)
//    emptyStateView.isVisible = movies.isEmpty() Just an idea to show something instead of empty list
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    this.menuInflater.inflate(R.menu.overflow_menu, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    if (item.itemId == R.id.logout) {
      viewModel.logOut()
      viewModel.stopSynchronization()
      navigateToLogin()
    }
    return false
  }

  private fun initMovieList() {
    moviesRecyclerView.layoutManager = GridLayoutManager(this, 2)
    moviesRecyclerView.adapter = movieAdapter
    moviesRecyclerView.addOnScrollListener(LazyLoadingListener {
      viewModel.fetchMovies()
    })
    viewModel.getMovies()
  }

  private fun movieClicked(movie: Movie) {
    startMovieDetailsActivity(
      this,
      movie.id
    )
  }

  private fun navigateToLogin() {
    startLoginActivity(this)
    finish()
  }
}