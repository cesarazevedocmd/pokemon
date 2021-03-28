package com.example.pokemon.util.extension

import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide

fun AppCompatImageView.loadImage(url: String) {
    if (this.context != null) {
        Glide
            .with(this.context)
            .load(url)
            .into(this)
    }
}