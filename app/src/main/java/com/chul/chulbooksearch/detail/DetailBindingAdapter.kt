package com.chul.chulbooksearch.detail

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.chul.chulbooksearch.view.SquareImageView

object DetailBindingAdapter {

    @BindingAdapter("price", "discount")
    @JvmStatic fun TextView.setPrice(price: String?, discount: String?) {
        this.text = "$price / $discount"
    }

    @BindingAdapter("image")
    @JvmStatic fun ImageView.setImage(url: String?) {
        if(!url.isNullOrEmpty()) {
            Glide.with(this).load(url).fitCenter().into(this)
        }
    }
}