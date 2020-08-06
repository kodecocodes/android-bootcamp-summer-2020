package codes.jenn.movieapp.di

import codes.jenn.movieapp.networking.MovieApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkingModule = module {
  single {
    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
  }

  single {
    OkHttpClient.Builder()
      .addInterceptor(get<HttpLoggingInterceptor>())
      .build()
  }

  single<Converter.Factory> { GsonConverterFactory.create() }

  single {
    Retrofit.Builder()
      .baseUrl(BASE_URL)
      .client(get())
      .addConverterFactory(get())
      .build()
  }

  single { get<Retrofit>().create(MovieApiService::class.java) }
}

private const val BASE_URL = "https://api.themoviedb.org/"