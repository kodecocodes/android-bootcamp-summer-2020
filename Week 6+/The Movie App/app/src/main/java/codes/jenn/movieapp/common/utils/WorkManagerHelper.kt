package codes.jenn.movieapp.common.utils

import android.content.Context
import androidx.work.*
import codes.jenn.movieapp.worker.SynchronizeMoviesWorker
import java.util.concurrent.TimeUnit

class WorkManagerHelper(private val context: Context) {

  private val workManager by lazy { WorkManager.getInstance(context) }

  fun setUpSynchronization() {
    val constraints = buildConstraints()
    val worker = buildWorker(constraints)

    workManager.enqueueUniquePeriodicWork(
      SynchronizeMoviesWorker.WORKER_ID,
      ExistingPeriodicWorkPolicy.KEEP,
      worker
    )
  }
  private fun buildConstraints(): Constraints {
    return Constraints.Builder()
      .setRequiresStorageNotLow(true)
      .setRequiresBatteryNotLow(true)
      .setRequiredNetworkType(NetworkType.CONNECTED)
      .build()
  }

  private fun buildWorker(constraints: Constraints): PeriodicWorkRequest {
    return PeriodicWorkRequestBuilder<SynchronizeMoviesWorker>(15, TimeUnit.MINUTES)
      .setConstraints(constraints)
      .build()
  }

  fun stopSynchronization() {
    workManager.cancelUniqueWork(SynchronizeMoviesWorker.WORKER_ID)
  }
}