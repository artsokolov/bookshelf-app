package com.example.bookshelfapp.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.bookshelfapp.data.responses.BookResponse

@Composable
fun HomeScreen(
    bookShelfUiState: BookShelfUiState,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    when(bookShelfUiState) {
        is BookShelfUiState.Success -> ResultScreen(
            books = bookShelfUiState.books,
            modifier = Modifier.fillMaxSize().padding(contentPadding)
        )
        is BookShelfUiState.Loading -> LoadingScreen(
            modifier = Modifier.fillMaxSize()
        )
        is BookShelfUiState.Error -> ErrorScreen(
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun ResultScreen(
    books: List<BookResponse>,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 100.dp)
    ) {
        items(books) {
            AsyncImage(
                model = it.volumeInfo.imageLinks.thumbnail.replace("http://", "https://"),
                contentDescription = null
            )
        }
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Text(
        text = "Loading"
    )
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Text(
        text = "Error"
    )
}