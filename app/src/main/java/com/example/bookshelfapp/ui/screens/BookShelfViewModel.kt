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
import com.example.bookshelfapp.data.BooksRepository
import com.example.bookshelfapp.data.responses.BookResponse
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface BookShelfUiState {
    data class Success(val books: List<BookResponse>) : BookShelfUiState
    object Error : BookShelfUiState
    object Loading : BookShelfUiState
}

class BookShelfViewModel(
    private val booksRepository: BooksRepository
): ViewModel() {

    var booksShelfUiState: BookShelfUiState by mutableStateOf(BookShelfUiState.Loading)
        private set

    init {
        getBooks()
    }

    fun getBooks() {
        viewModelScope.launch {
            booksShelfUiState = try {
                val listResult = booksRepository.getBooks(
                    "kotlin"
                )

                BookShelfUiState.Success(
                    listResult
                )
            } catch (e: IOException) {
                BookShelfUiState.Error
            }
        }
    }

    companion object {
        val factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as BookShelfApplication)
                val booksRepository = application.container.booksRepository
                BookShelfViewModel(booksRepository = booksRepository)
            }
        }
    }
}