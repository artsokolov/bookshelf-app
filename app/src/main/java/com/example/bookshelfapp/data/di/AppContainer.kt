package com.example.bookshelfapp.data.di

import com.example.bookshelfapp.domain.repository.BooksRepository

interface AppContainer {
    val booksRepository: BooksRepository
}