package codes.jenn.movieapp.login.viewmodel

import codes.jenn.movieapp.common.utils.Validator
import codes.jenn.movieapp.repository.UserRepository
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class LoginViewModelTest : BaseViewModelTest() {

  private lateinit var loginViewModel: LoginViewModel

  @Mock private lateinit var credentialsValidator: Validator
  @Mock private lateinit var userRepository: UserRepository

  @Before
  fun setUp() {
    MockitoAnnotations.initMocks(this)
    loginViewModel = LoginViewModel(credentialsValidator, userRepository)
  }

  @Test
  fun `viewState is set to UserLoggedIn if user is logged in`() {
    `when`(userRepository.isUserLoggedIn()).thenReturn(true)
    loginViewModel.checkIfUserLoggedIn()
    assertTrue(loginViewModel.getLoginViewState().value == UserLoggedIn)
  }

  @Test
  fun `viewState is set to InvalidUsername if username is invalid`() {
    credentialsValidator.setCredentials("", "")
    loginViewModel.checkUsername()
    assertTrue(loginViewModel.getLoginViewState().value == InvalidUsername)
  }

  @Test
  fun `viewState is set to InvalidPassword if password is invalid`() {
    credentialsValidator.setCredentials("", "")
    loginViewModel.checkPassword()
    assertTrue(loginViewModel.getLoginViewState().value == InvalidPassword)
  }
}