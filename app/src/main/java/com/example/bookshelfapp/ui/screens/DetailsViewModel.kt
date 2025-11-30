package com.example.bookshelfapp.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bookshelfapp.BookShelfApplication
import com.example.bookshelfapp.domain.repository.BooksRepository
import com.example.bookshelfapp.domain.entity.Book
import kotlinx.coroutines.launch
import java.io.IOException


sealed interface BookDetailsUiState {
    data class Success(val book: Book) : BookDetailsUiState
    object Error : BookDetailsUiState
    object Loading : BookDetailsUiState
}

class BookDetailsViewModel(
    private val booksRepository: BooksRepository,
    private val bookId: String
): ViewModel() {
    var bookDetailsUiState: BookDetailsUiState by mutableStateOf(BookDetailsUiState.Loading)
        private set

    init {
        loadBook()
    }

    fun loadBook() {
        viewModelScope.launch {
            bookDetailsUiState = try {
                val book = booksRepository.getBook(bookId)

                BookDetailsUiState.Success(book)
            } catch (e: IOException) {
                BookDetailsUiState.Error
            }
        }
    }

    companion object {
        fun provideFactory(
            bookId: String
        ): ViewModelProvider.Factory =
            viewModelFactory {
                initializer {
                    val application = (this[APPLICATION_KEY] as BookShelfApplication)
                    val booksRepository = application.container.booksRepository

                    BookDetailsViewModel(
                        booksRepository = booksRepository,
                        bookId = bookId
                    )
                }
            }
    }
}