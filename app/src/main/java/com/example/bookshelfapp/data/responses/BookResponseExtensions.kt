package com.example.bookshelfapp.data.responses

import androidx.core.text.HtmlCompat
import com.example.bookshelfapp.data.ensureHttps
import com.example.bookshelfapp.domain.entity.Book
import com.example.bookshelfapp.domain.entity.BookImages


fun BookResponse.toDomainEntity(): Book {
    val vi = this.volumeInfo

    val cleanedDescription: String? = vi.description?.let {
        HtmlCompat.fromHtml(it, HtmlCompat.FROM_HTML_MODE_LEGACY)
            .toString()
            .trim()
            .replace("\n\n", "\n")
    }

    return Book(
        id = this.id,
        title = vi.title,
        authors = vi.authors,
        images = BookImages(
            thumbnail = vi.imageLinks.thumbnail.ensureHttps(),
            large = vi.imageLinks.large?.ensureHttps()
        ),
        pageCount = vi.pageCount,
        description = cleanedDescription
    )
}

