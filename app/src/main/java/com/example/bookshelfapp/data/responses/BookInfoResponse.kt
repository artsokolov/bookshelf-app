package com.example.bookshelfapp.data.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BookInfoResponse (
    @SerialName("imageLinks")
    val imageLinks: ImageResponse
)