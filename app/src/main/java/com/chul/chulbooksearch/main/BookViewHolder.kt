package com.chul.chulbooksearch.main

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.chul.chulbooksearch.data.Book
import com.chul.chulbooksearch.databinding.ItemSearchBookBinding

class BookViewHolder(private val mBinding: ItemSearchBookBinding): RecyclerView.ViewHolder(mBinding.root) {

    fun bind(viewModel: SearchViewModel, book: Book) {
        mBinding.vm = viewModel
        mBinding.book = book
        mBinding.executePendingBindings()
    }
}