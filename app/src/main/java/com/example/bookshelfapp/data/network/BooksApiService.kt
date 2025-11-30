package com.example.bookshelfapp.data.network

import com.example.bookshelfapp.data.responses.BookResponse
import com.example.bookshelfapp.data.responses.VolumeResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BooksApiService {
    @GET("books/v1/volumes")
    suspend fun getBooks(
        @Query("q")
        searchQuery: String
    ): VolumeResponse

    @GET("books/v1/volumes/{bookId}")
    suspend fun getBook(
        @Path("bookId")
        bookId: String
    ): BookResponse
}
