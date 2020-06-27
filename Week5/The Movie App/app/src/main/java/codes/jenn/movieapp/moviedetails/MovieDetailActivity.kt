package codes.jenn.movieapp.moviedetails

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import codes.jenn.movieapp.R
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        titleDetailTextView.text = intent.getStringExtra(MOVIE_TITLE_KEY)
        summaryDetailTextView.text = intent.getStringExtra(MOVIE_SUMMARY_KEY)
        releaseDateTextView.text =
            "Release Date: ${intent.getStringExtra(MOVIE_RELEASE_DATE_KEY)}"
        detailImageView.setImageResource(intent.getIntExtra(MOVIE_POSTER_KEY, 0))
    }
}

private const val MOVIE_TITLE_KEY = "movie_title"
private const val MOVIE_SUMMARY_KEY = "movie_summary"
private const val MOVIE_RELEASE_DATE_KEY = "move_release_date"
private const val MOVIE_POSTER_KEY = "movie_poster"