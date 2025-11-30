package com.example.bookshelfapp.data.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BookResponse(
    val id: String,
    val volumeInfo: BookInfoResponse
)
