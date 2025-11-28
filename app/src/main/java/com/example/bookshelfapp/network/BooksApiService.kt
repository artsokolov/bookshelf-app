package com.example.bookshelfapp.network

import com.example.bookshelfapp.data.responses.VolumeResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface BooksApiService {
    @GET("books/v1/volumes")
    suspend fun getBooks(
        @Query("q")
        searchQuery: String
    ): VolumeResponse
}
