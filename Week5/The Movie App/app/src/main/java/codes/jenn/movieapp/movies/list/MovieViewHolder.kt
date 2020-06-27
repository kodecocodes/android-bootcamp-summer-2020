package codes.jenn.movieapp.movies.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import codes.jenn.movieapp.movies.model.Movie
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.movie_list_item.view.*

class MovieViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
    LayoutContainer {

    fun bind(movie: Movie, onMovieClick: (Movie) -> Unit) = with(containerView) {
        titleTextView.text = movie.title
        posterImageView.setImageResource(movie.image)
        rootView.setOnClickListener { onMovieClick(movie) }
    }
}