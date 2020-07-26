package codes.jenn.movieapp.common.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

inline fun <T> LiveData<T>.subscribe(
  lifecycleOwner: LifecycleOwner,
  crossinline onDataChanged: (T) -> Unit
) {
  observe(lifecycleOwner, Observer { onDataChanged(it) })
}