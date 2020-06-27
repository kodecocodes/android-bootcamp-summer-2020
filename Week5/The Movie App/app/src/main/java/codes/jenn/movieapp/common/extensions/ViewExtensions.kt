package codes.jenn.movieapp.common.extensions

import android.view.View

fun View.onClick(onClickAction: () -> Unit) {
  setOnClickListener { onClickAction() }
}