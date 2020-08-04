package codes.jenn.movieapp.di

import codes.jenn.movieapp.repository.MovieRepository
import codes.jenn.movieapp.repository.MovieRepositoryImpl
import codes.jenn.movieapp.repository.UserRepository
import codes.jenn.movieapp.repository.UserRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
  single<MovieRepository> { MovieRepositoryImpl(get(), get()) }
  single<UserRepository> { UserRepositoryImpl(get()) }
}