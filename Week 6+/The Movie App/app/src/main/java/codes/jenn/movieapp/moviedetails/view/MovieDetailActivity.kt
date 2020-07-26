package codes.jenn.movieapp.moviedetails.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import codes.jenn.movieapp.App
import codes.jenn.movieapp.R
import codes.jenn.movieapp.common.extensions.subscribe
import codes.jenn.movieapp.moviedetails.viewmodel.DetailsViewModel
import codes.jenn.movieapp.moviedetails.viewmodel.DetailsViewState
import codes.jenn.movieapp.moviedetails.viewmodel.Loading
import codes.jenn.movieapp.moviedetails.viewmodel.Success
import codes.jenn.movieapp.movies.model.Movie
import codes.jenn.movieapp.networking.IMAGE_BASE_URL
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_movie_detail.*

private const val MOVIE_ID_KEY = "movie_id"

fun startMovieDetailsActivity(from: Context, movieId: String) =
  from.startActivity(Intent(from, MovieDetailActivity::class.java).apply {
    putExtra(MOVIE_ID_KEY, movieId)
  })

class MovieDetailActivity : AppCompatActivity() {

  private val movieId by lazy { intent.getStringExtra(MOVIE_ID_KEY) }
  private val viewModel by viewModels<DetailsViewModel> { App.movieDetailsViewModelFactory }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_movie_detail)
    subscribeToData()
    viewModel.getMovieById(movieId)
  }

  private fun subscribeToData() {
    viewModel.getDetailsViewState().subscribe(this, ::onDetailsViewStateChanged)
  }

  private fun onDetailsViewStateChanged(viewState: DetailsViewState) {
    when (viewState) {
      Loading -> showLoading()
      is Success -> {
        if (viewState.movie != null)
          displayMovieDetails(viewState.movie)
        else showEmptyState()
      }
    }
  }

  private fun showLoading() {
    // Show progress bar here if you want
  }

  private fun hideLoading() {
    // Hide progress here
  }

  private fun showEmptyState() {
    // Show empty state here
  }

  private fun hideEmptyState() {
    // Hide empty state here
  }

  private fun displayMovieDetails(movie: Movie) {
    hideLoading()
    hideEmptyState()
    titleDetailTextView.text = movie.title
    summaryDetailTextView.text = movie.description
    releaseDateTextView.text = "Release Date: ${movie.releaseDate}"
    Glide.with(this).load(IMAGE_BASE_URL + movie.image).into(detailImageView)
  }
}

