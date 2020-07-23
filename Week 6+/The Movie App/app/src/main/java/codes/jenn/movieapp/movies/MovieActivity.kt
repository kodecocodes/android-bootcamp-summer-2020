package codes.jenn.movieapp.movies

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.work.*
import codes.jenn.movieapp.App
import codes.jenn.movieapp.R
import codes.jenn.movieapp.login.startLoginActivity
import codes.jenn.movieapp.moviedetails.startMovieDetailsActivity
import codes.jenn.movieapp.movies.list.LazyLoadingListener
import codes.jenn.movieapp.movies.list.MovieAdapter
import codes.jenn.movieapp.movies.model.Movie
import codes.jenn.movieapp.repository.UserRepository
import codes.jenn.movieapp.worker.SynchronizeMoviesWorker
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

fun startMovieActivity(from: Context) = from.startActivity(Intent(from, MovieActivity::class.java))

class MovieActivity : AppCompatActivity() {

  private val viewModel by lazy {
    ViewModelProviders.of(this, App.viewModelFactory)
      .get(MovieViewModel::class.java)
  }
  private val movieAdapter by lazy { MovieAdapter(::movieClicked) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    initMovieList()
    viewModel.fetchMovies()

    setUpSynchronization()
  }

  private fun setUpSynchronization() {
    val constraints = buildConstraints()
    val worker = buildWorker(constraints)

    val workManager = WorkManager.getInstance(this)

    workManager.enqueueUniquePeriodicWork(
      SynchronizeMoviesWorker.WORKER_ID,
      ExistingPeriodicWorkPolicy.KEEP,
      worker
    )
  }

  private fun buildConstraints(): Constraints {
    return Constraints.Builder()
      .setRequiresStorageNotLow(true)
      .setRequiresBatteryNotLow(true)
      .setRequiredNetworkType(NetworkType.CONNECTED)
      .build()
  }

  private fun buildWorker(constraints: Constraints): PeriodicWorkRequest {
    return PeriodicWorkRequestBuilder<SynchronizeMoviesWorker>(15, TimeUnit.MINUTES)
      .setConstraints(constraints)
      .build()
  }

  private fun stopSynchronization() {
    val workManager = WorkManager.getInstance(this)

    workManager.cancelUniqueWork(SynchronizeMoviesWorker.WORKER_ID)
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    this.menuInflater.inflate(R.menu.overflow_menu, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    if (item.itemId == R.id.logout) {
      viewModel.logOut()
      stopSynchronization()
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

    viewModel.getMovies().observe(this, Observer { value ->
      if (value != null) {
        movieAdapter.setMovies(value)
      }
    })
  }

  private fun movieClicked(movie: Movie) {
    startMovieDetailsActivity(this, movie.id)
  }

  private fun navigateToLogin() {
    startLoginActivity(this)
    finish()
  }
}