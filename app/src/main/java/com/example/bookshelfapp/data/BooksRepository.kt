package com.example.bookshelfapp.data

import com.example.bookshelfapp.data.responses.BookResponse

interface BooksRepository {
    suspend fun getBooks(searchQuery: String): List<BookResponse>
}