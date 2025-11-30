package com.example.bookshelfapp.ui.screens

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Size
import com.example.bookshelfapp.R
import com.example.bookshelfapp.domain.entity.Book
import com.example.bookshelfapp.ui.components.ErrorScreen
import com.example.bookshelfapp.ui.components.LoadingScreen

@Composable
fun DetailsScreen(
    bookDetailsUiState: BookDetailsUiState,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    when(bookDetailsUiState) {
        is BookDetailsUiState.Success -> DetailsResultScreen(
            book = bookDetailsUiState.book,
            modifier = modifier.padding(contentPadding)
        )
        is BookDetailsUiState.Loading -> LoadingScreen(
            modifier = modifier.fillMaxSize()
        )
        is BookDetailsUiState.Error -> ErrorScreen(
            modifier = modifier.fillMaxSize()
        )
    }
}

@SuppressLint("ContextCastToActivity")
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun DetailsResultScreen(
    book: Book,
    modifier: Modifier = Modifier
) {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    val scrollState = rememberScrollState()

    if (isLandscape) {
        Row(
            modifier = modifier
                .padding(dimensionResource(R.dimen.padding_medium))
                .verticalScroll(scrollState)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(book.images.large ?: book.images.thumbnail)
                    .size(Size.ORIGINAL)
                    .build(),
                contentDescription = null,
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(2 / 3f)
                    .clip(RoundedCornerShape(12.dp))
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = dimensionResource(R.dimen.padding_medium))
            ) {
                BookDetailsText(book)
            }
        }
    } else {
        Column(
            modifier = modifier
                .padding(dimensionResource(R.dimen.padding_medium))
                .verticalScroll(scrollState)
        ) {
            AsyncImage(
                model = book.images.thumbnail,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(2 / 3f)
                    .clip(RoundedCornerShape(12.dp)),
            )

            Column(modifier = Modifier.padding(
                top = dimensionResource(R.dimen.padding_medium)
            )) {
                BookDetailsText(book)
            }
        }
    }
}

@Composable
private fun BookDetailsText(book: Book) {
    Text(
        text = book.title,
        modifier = Modifier.fillMaxWidth(),
        style = MaterialTheme.typography.headlineLarge,
        textAlign = TextAlign.Center
    )

    Text(
        text = book.authors.joinToString(),
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center
    )

    Text(
        text = book.description ?: stringResource(R.string.no_description),
        modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_medium))
    )
}
