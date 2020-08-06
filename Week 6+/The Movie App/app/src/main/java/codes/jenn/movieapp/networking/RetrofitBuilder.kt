package codes.jenn.movieapp.networking

import java.util.*

const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/original"
private const val API_KEY = "eba0b836faa0e752a2d5177c38cf4b41"
private const val KEY_PAGE = "page"
private const val KEY_API = "api_key"

fun getMoviesQuery(page: Int): Map<String, String> {
  val queryMap = HashMap<String, String>()

  queryMap[KEY_PAGE] = page.toString()
  queryMap[KEY_API] = API_KEY

  return queryMap
}