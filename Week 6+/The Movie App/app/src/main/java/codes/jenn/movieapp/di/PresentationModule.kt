package codes.jenn.movieapp.di

import codes.jenn.movieapp.login.viewmodel.LoginViewModel
import codes.jenn.movieapp.moviedetails.viewmodel.DetailsViewModel
import codes.jenn.movieapp.movies.viewmodel.MovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
  viewModel { MovieViewModel(get(), get(), get()) }
  viewModel { LoginViewModel(get(), get()) }
  viewModel { DetailsViewModel(get()) }
}