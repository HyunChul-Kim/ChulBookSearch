package com.chul.chulbooksearch.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chul.chulbooksearch.R
import com.chul.chulbooksearch.data.Book
import com.chul.chulbooksearch.databinding.ItemSearchBookBinding

class SearchResultAdapter(private val viewModel: SearchViewModel): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val bookList = ArrayList<Book>()

    fun addBooks(list: List<Book>) {
        if(viewModel.isInitialSearch()) {
            val removedSize = bookList.size
            bookList.clear()
            notifyItemRangeRemoved(1, removedSize)
        }
        val insertPosition = bookList.size + 1
        bookList.addAll(list)
        notifyItemInserted(insertPosition)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            VIEW_TYPE_HEADER -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_search_header, parent, false)
                return object : HeaderViewHolder(view) {
                    override fun onSearch(text: String) {
                        viewModel.requestBookSearch(text)
                    }
                }
            }
            else -> {
                val itemBookBinding = ItemSearchBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                BookViewHolder(itemBookBinding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(getItemViewType(position)) {
            VIEW_TYPE_BOOK -> {
                (holder as BookViewHolder).bind(viewModel, bookList[position - 1])
            }
        }
    }

    override fun getItemCount(): Int {
        return bookList.size + 1
    }

    override fun getItemViewType(position: Int): Int {
        return if(position == 0) {
            VIEW_TYPE_HEADER
        } else {
            VIEW_TYPE_BOOK
        }
    }

    companion object {
        const val VIEW_TYPE_HEADER = 0
        const val VIEW_TYPE_BOOK = 1
    }
}