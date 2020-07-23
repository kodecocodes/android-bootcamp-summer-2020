package codes.jenn.movieapp.networking

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

private const val BASE_URL = "https://api.themoviedb.org/"
const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/original"
private const val API_KEY = "eba0b836faa0e752a2d5177c38cf4b41"
private const val KEY_PAGE = "page"
private const val KEY_API = "api_key"

fun buildClient(): OkHttpClient =
  OkHttpClient.Builder()
    .addInterceptor(HttpLoggingInterceptor().apply {
      level = HttpLoggingInterceptor.Level.BODY
    })
    .build()

fun buildRetrofit(): Retrofit {
  return Retrofit.Builder()
    .client(buildClient())
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()
}

fun getMoviesQuery(page: Int): Map<String, String> {
  val queryMap = HashMap<String, String>()

  queryMap[KEY_PAGE] = page.toString()
  queryMap[KEY_API] = API_KEY

  return queryMap
}

fun buildApiService(): MovieApiService =
  buildRetrofit().create(MovieApiService::class.java)