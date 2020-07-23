package codes.jenn.movieapp.movies.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import codes.jenn.movieapp.movies.model.Movie
import codes.jenn.movieapp.networking.IMAGE_BASE_URL
import com.bumptech.glide.Glide
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.movie_list_item.view.*

class MovieViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
  LayoutContainer {

  fun bind(movie: Movie, onMovieClick: (Movie) -> Unit) = with(containerView) {
    titleTextView.text = movie.title
    Glide.with(this).load(IMAGE_BASE_URL + movie.image).into(posterImageView)
    rootView.setOnClickListener { onMovieClick(movie) }
  }
}