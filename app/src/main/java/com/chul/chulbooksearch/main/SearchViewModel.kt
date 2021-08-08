package com.chul.chulbooksearch.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chul.chulbooksearch.Event
import com.chul.chulbooksearch.data.Book
import com.chul.chulbooksearch.network.ApiFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel: ViewModel() {

    private val _bookList = MutableLiveData<List<Book>>()
    val bookList: LiveData<List<Book>> = _bookList

    private val _openDetailEvent = MutableLiveData<Event<Book>>()
    val openDetailEvent: LiveData<Event<Book>> = _openDetailEvent

    private var keyword = ""
    private var start = 1
    private var totalPage = 0
    var display = 10

    fun requestBookSearch(text: String) {
        keyword = text
        start = 1
        totalPage = 0
        CoroutineScope(Dispatchers.Main).launch {
            val response = ApiFactory.bookService.getBooks(keyword, start, display)
            val items = response.items
            totalPage = response.total?.toInt() ?: 0
            if(items != null) {
                _bookList.value = items
            }
        }
    }

    fun requestNextBookSearch() {
        if(!hasNext()) return
        start += display
        CoroutineScope(Dispatchers.Main).launch {
            val response = ApiFactory.bookService.getBooks(keyword, start, display)
            val items = response.items
            if(items != null) {
                _bookList.value = items
            }
        }
    }

    fun hasNext() = start < totalPage

    fun openDetail(book: Book) {
        _openDetailEvent.value = Event(book)
    }

    fun isInitialSearch() = start == 1

}