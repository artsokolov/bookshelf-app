package com.example.bookshelfapp.domain.repository

import com.example.bookshelfapp.domain.entity.Book

interface BooksRepository {
    suspend fun getBooks(searchQuery: String): List<Book>

    suspend fun getBook(bookId: String): Book
}