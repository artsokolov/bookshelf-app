package com.example.bookshelfapp.data.responses

import kotlinx.serialization.Serializable

@Serializable
data class ImageResponse(
    val thumbnail: String,
    val large: String? = null
)