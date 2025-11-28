package com.example.bookshelfapp.data.responses

import kotlinx.serialization.Serializable

@Serializable
data class VolumeResponse(
    val items: List<BookResponse>
)