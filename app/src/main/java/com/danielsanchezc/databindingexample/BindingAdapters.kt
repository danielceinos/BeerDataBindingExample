package com.danielsanchezc.databindingexample

import android.databinding.BindingAdapter
import android.widget.ImageView
import android.widget.TextView

@BindingAdapter("imageUrl")
fun setUrlImage(imageView: ImageView, url: String?) {
    GlideApp.with(imageView.context)
        .load(url)
        .placeholder(R.drawable.ic_beer)
        .into(imageView)
}

@BindingAdapter("imageUrl", "handler", requireAll = true)
fun setUrlImageWithSharedTransition(imageView: ImageView, imageUrl: String?, handler: () -> Unit) {
    imageView.load(imageUrl) {
        handler()
    }
}

@BindingAdapter("alc")
fun bindsAlc(textView: TextView, value: Any) {
    textView.text = value.toString()

    if (value is Double)
        textView.setTextColor(
            textView.context.resources.getColor(
                when (value) {
                    0.0 -> R.color.alc_free
                    in 0.0..4.0 -> R.color.alc_low
                    in 4.0..6.0 -> R.color.alc_medium
                    else -> R.color.alc_high
                }
            )
        )
}







