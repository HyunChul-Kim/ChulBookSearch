package com.chul.chulbooksearch.network

import com.chul.chulbooksearch.data.BookSearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BookService {

    @GET("book.json")
    suspend fun getBooks(@Query("query") title: String, @Query("start") start: Int, @Query("display") display: Int): BookSearchResponse
}