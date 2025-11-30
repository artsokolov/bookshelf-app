package com.example.bookshelfapp.data.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BookInfoResponse (
    val title: String,
    @SerialName("imageLinks")
    val imageLinks: ImageResponse,
    val authors: List<String>,
    val pageCount: Int,
    val description: String? = null
)