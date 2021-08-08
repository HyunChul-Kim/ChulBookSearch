package com.chul.chulbooksearch

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SpaceItemDecoration @JvmOverloads constructor(
    private val orientation: Int = RecyclerView.VERTICAL,
    private val offset: Int = 0): RecyclerView.ItemDecoration() {

    private val exceptionViewTypes = ArrayList<Int>()

    fun setExceptionViewType(viewType: Int) {
        exceptionViewTypes.add(viewType)
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val childIndex = parent.getChildAdapterPosition(view)
        val lastIndex = parent.adapter?.itemCount ?: 0
        val viewType = parent.adapter?.getItemViewType(childIndex) ?: 0
        if(childIndex != lastIndex && !exceptionViewTypes.contains(viewType)) {
            if(orientation == RecyclerView.VERTICAL) {
                outRect.bottom = offset
            } else {
                outRect.right = offset
            }
        }
    }
}