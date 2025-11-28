package com.example.bookshelfapp.data

import com.example.bookshelfapp.data.responses.BookResponse
import com.example.bookshelfapp.network.BooksApiService

class NetworkBooksRepository(
    private val apiService: BooksApiService
): BooksRepository {
    override suspend fun getBooks(searchQuery: String): List<BookResponse> =
        apiService.getBooks(searchQuery = searchQuery).items
}
