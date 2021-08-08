package com.chul.chulbooksearch.data

data class BookSearchResponse(
    val total: String?,
    val start: String?,
    val display: String?,
    val items: List<Book>?
)