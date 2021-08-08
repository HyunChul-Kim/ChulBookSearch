package com.chul.chulbooksearch.main

import android.os.Build
import android.text.Html
import android.widget.TextView
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_COMPACT
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chul.chulbooksearch.data.Book
import com.chul.chulbooksearch.view.SquareImageView

object SearchBindingAdapter {

    @BindingAdapter("image")
    @JvmStatic fun SquareImageView.setImage(url: String?) {
        if(!url.isNullOrEmpty()) {
            Glide.with(this).load(url).fitCenter().into(this)
        }
    }

    @BindingAdapter("htmlText")
    @JvmStatic fun TextView.setHtmlText(text: String) {
        this.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(text, FROM_HTML_MODE_COMPACT).toString()
        } else {
            Html.fromHtml(text).toString()
        }
    }

    @BindingAdapter("bind")
    @JvmStatic fun RecyclerView.setData(items: List<Book>?) {
        if(items != null) {
            (this.adapter as? SearchResultAdapter)?.addBooks(items)
        }
    }
}