package com.chul.chulbooksearch

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class AutoLoadScrollListener: RecyclerView.OnScrollListener() {

    private var threshold = 5
    private var prevItemCount = 0
    private var isLoading = false

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val layoutManager = recyclerView.layoutManager as? LinearLayoutManager ?: return

        val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
        val totalItemCount = layoutManager.itemCount

        if(totalItemCount != prevItemCount) {
            isLoading = false
            prevItemCount = totalItemCount
        }

        if(!isLoading && lastVisibleItemPosition + threshold >= totalItemCount) {
            isLoading = true
            loadMore()
        }
    }

    abstract fun loadMore()
}