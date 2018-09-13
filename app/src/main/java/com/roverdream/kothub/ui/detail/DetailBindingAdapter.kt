package com.roverdream.kothub.ui.detail

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.roverdream.kothub.extensions.loadImage

@BindingAdapter("android:src")
fun setImageBinding(view: ImageView, url: String) {
    view.loadImage(url)
}
