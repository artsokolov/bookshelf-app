package com.example.bookshelfapp.data.repository

import androidx.compose.ui.res.integerResource
import com.example.bookshelfapp.data.network.BooksApiService
import com.example.bookshelfapp.data.responses.BookResponse
import com.example.bookshelfapp.data.responses.toDomainEntity
import com.example.bookshelfapp.domain.entity.Book
import com.example.bookshelfapp.domain.repository.BooksRepository

class NetworkBooksRepository(
    private val apiService: BooksApiService
): BooksRepository {
    override suspend fun getBooks(searchQuery: String): List<Book> {
        return apiService
            .getBooks(searchQuery = searchQuery)
            .items
            .map { it.toDomainEntity() }
    }

    override suspend fun getBook(bookId: String): Book {
        return apiService.getBook(bookId).toDomainEntity()
    }
}
