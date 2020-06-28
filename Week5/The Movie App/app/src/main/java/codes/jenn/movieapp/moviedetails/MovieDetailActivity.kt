package codes.jenn.movieapp.moviedetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import codes.jenn.movieapp.R
import codes.jenn.movieapp.movies.model.Movie
import codes.jenn.movieapp.repository.MovieRepository
import kotlinx.android.synthetic.main.activity_movie_detail.*

fun startMovieDetailsActivity(from: Context, movieId: Int?) =
  from.startActivity(Intent(from, MovieDetailActivity::class.java).apply {
    putExtra(MOVIE_ID_KEY, movieId)
  })

class MovieDetailActivity : AppCompatActivity() {

  private val movieId by lazy { intent.getIntExtra(MOVIE_ID_KEY, -1) }
  private val repository by lazy { MovieRepository() }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_movie_detail)

    val movie = repository.getMovieById(movieId)
    displayMovieDetails(movie)
  }

  private fun displayMovieDetails(movie: Movie) {
    titleDetailTextView.text = movie.title
    summaryDetailTextView.text = movie.summary
    releaseDateTextView.text = "Release Date: ${movie.releaseDate}"
    detailImageView.setImageResource(movie.image)
  }
}

private const val MOVIE_ID_KEY = "movie_id"