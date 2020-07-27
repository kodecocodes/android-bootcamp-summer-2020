package codes.jenn.movieapp.movies.viewmodel

import codes.jenn.movieapp.common.utils.WorkManagerHelper
import codes.jenn.movieapp.login.viewmodel.BaseViewModelTest
import codes.jenn.movieapp.repository.MovieRepository
import codes.jenn.movieapp.repository.UserRepository
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class MovieViewModelTest : BaseViewModelTest() {

  private val movieRepository: MovieRepository = mock()
  private val userRepository: UserRepository = mock()
  private val workManagerHelper: WorkManagerHelper = mock()
  private lateinit var movieViewModel: MovieViewModel

  @Before
  fun setUp() {
    MockitoAnnotations.initMocks(this)
    movieViewModel = MovieViewModel(movieRepository, userRepository, workManagerHelper)
  }

  @Test
  fun `getMovies calls repository to get movies`() {
    movieViewModel.getMovies()
    verify(movieRepository).getAllMovies()
  }

  @Test
  fun `test logout sets userLoggedIn flag to false`() {
    movieViewModel.logOut()
    verify(userRepository).setUserLoggedIn(false)
  }

  @Test
  fun `test fetchMovies calls repository to load movies for page`() {
    testCoroutineRule.runBlockingTest {
      movieViewModel.fetchMovies()
      verify(movieRepository).loadMoviesForPage(1)
    }
  }
}