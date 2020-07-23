package codes.jenn.movieapp.moviedetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import codes.jenn.movieapp.App
import codes.jenn.movieapp.R
import codes.jenn.movieapp.movies.model.Movie
import codes.jenn.movieapp.networking.IMAGE_BASE_URL
import codes.jenn.movieapp.repository.MovieRepositoryImpl
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.coroutines.launch

private const val MOVIE_ID_KEY = "movie_id"

fun startMovieDetailsActivity(from: Context, movieId: String) =
  from.startActivity(Intent(from, MovieDetailActivity::class.java).apply {
    putExtra(MOVIE_ID_KEY, movieId)
  })

class MovieDetailActivity : AppCompatActivity() {

  private val movieId by lazy { intent.getIntExtra(MOVIE_ID_KEY, -1) }
  private val repository by lazy { App.repository }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_movie_detail)

    lifecycleScope.launch {
      val movie = repository.getMovieById(movieId)
      displayMovieDetails(movie)
    }
  }

  private fun displayMovieDetails(movie: Movie) {
    titleDetailTextView.text = movie.title
    summaryDetailTextView.text = movie.description
    releaseDateTextView.text = "Release Date: ${movie.releaseDate}"
    Glide.with(this).load(IMAGE_BASE_URL + movie.image).into(detailImageView)
  }
}

