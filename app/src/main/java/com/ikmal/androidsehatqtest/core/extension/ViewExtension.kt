package com.ikmal.androidsehatqtest.core.extension

import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ikmal.androidsehatqtest.R


fun AppCompatImageView.loadImage(uri: String) =
    Glide.with(this).load(uri).placeholder(R.drawable.ic_placeholder).into(this)

fun AppCompatImageView.loadCircularImage(uri: String) =
    Glide.with(this).load(uri).apply(RequestOptions.circleCropTransform())
        .placeholder(R.drawable.ic_placeholder).into(
            this
        )