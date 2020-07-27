package codes.jenn.movieapp.moviedetails.viewmodel

import codes.jenn.movieapp.login.viewmodel.BaseViewModelTest
import codes.jenn.movieapp.movies.model.Movie
import codes.jenn.movieapp.repository.MovieRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class DetailsViewModelTest : BaseViewModelTest() {

  private lateinit var detailsViewModel: DetailsViewModel

  @Mock private lateinit var movieRepository: MovieRepository

  @Before
  fun setUp() {
    MockitoAnnotations.initMocks(this)
    detailsViewModel = DetailsViewModel(movieRepository)
  }

  @Test
  fun `test getMovieById calls repository to get movie`() = testCoroutineRule.runBlockingTest {
    detailsViewModel.getMovieById("100")
    verify(movieRepository).getMovieById("100")
  }

  @Test
  fun `test getMovieById sets livedata value to Success`() = testCoroutineRule.runBlockingTest {
    val testMovie =
      Movie("100", "Test title", "test desc", 10, 8.8f, "", "27.8.2020.", "popular")
    `when`(movieRepository.getMovieById(ArgumentMatchers.any())).thenReturn(testMovie)
    detailsViewModel.getMovieById("100")
    Assert.assertTrue(detailsViewModel.getDetailsViewState().value == Success(testMovie))
  }
}