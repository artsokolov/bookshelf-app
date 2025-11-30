package com.example.bookshelfapp.ui.screens

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.bookshelfapp.R
import com.example.bookshelfapp.data.responses.BookResponse
import com.example.bookshelfapp.domain.entity.Book
import com.example.bookshelfapp.ui.components.ErrorScreen
import com.example.bookshelfapp.ui.components.LoadingScreen

@Composable
fun HomeScreen(
    bookShelfUiState: BookShelfUiState,
    onBookClick: (id: String) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    when(bookShelfUiState) {
        is BookShelfUiState.Success -> HomeResultScreen(
            books = bookShelfUiState.books,
            onBookClick = onBookClick,
            modifier = modifier.padding(contentPadding)
        )
        is BookShelfUiState.Loading -> LoadingScreen(
            modifier = modifier.fillMaxSize()
        )
        is BookShelfUiState.Error -> ErrorScreen(
            modifier = modifier.fillMaxSize()
        )
    }
}

@SuppressLint("ContextCastToActivity")
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun HomeResultScreen(
    books: List<Book>,
    onBookClick: (id: String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
        modifier = modifier.padding(dimensionResource(R.dimen.padding_medium))
    ) {
        items(books) {
            BookListItem(book = it, onBookClick = onBookClick)
        }
    }
}

@Composable
fun BookListItem(
    book: Book,
    onBookClick: (id: String) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = { onBookClick(book.id) },
        modifier = modifier
            .fillMaxWidth()
            .height(150.dp)
    ) {
        Row {
            AsyncImage(
                model = book.images.thumbnail,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(2 / 3f)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop,
            )

            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = book.title,
                    maxLines = 2,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = book.authors.joinToString(),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }

}
