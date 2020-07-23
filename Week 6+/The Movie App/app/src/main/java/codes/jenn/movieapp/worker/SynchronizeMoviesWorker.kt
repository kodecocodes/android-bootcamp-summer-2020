package codes.jenn.movieapp.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import codes.jenn.movieapp.App

class SynchronizeMoviesWorker(appContext: Context, workerParameters: WorkerParameters) :
  CoroutineWorker(appContext, workerParameters) {

  companion object {
    const val WORKER_ID = "SynchronizeMoviesWorkerID"
  }

  private val repository by lazy { App.repository }

  override suspend fun doWork(): Result {
    repository.clearMovies()

    return try {
      repository.loadMoviesForPage(1)

      Result.success()
    } catch (error: Throwable) {
      Result.failure()
    }
  }
}