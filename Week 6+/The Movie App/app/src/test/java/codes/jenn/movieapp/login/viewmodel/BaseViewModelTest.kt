package codes.jenn.movieapp.login.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.testcoroutinesrule.TestCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule

abstract class BaseViewModelTest {

  @get:Rule
  val rule = InstantTaskExecutorRule()

  @ExperimentalCoroutinesApi
  @get:Rule
  val testCoroutineRule = TestCoroutineRule()
}