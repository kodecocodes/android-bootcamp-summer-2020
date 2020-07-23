package codes.jenn.movieapp.networking

import codes.jenn.movieapp.movies.model.MovieList
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface MovieApiService {

  @GET("/3/movie/popular")
  suspend fun getPopularMovies(@QueryMap query: Map<String, String>): MovieList
}