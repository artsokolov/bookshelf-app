package com.example.bookshelfapp.domain.entity

data class Book(
    val id: String,
    val title: String,
    val images: BookImages,
    val authors: List<String>,
    val pageCount: Int,
    val description: String?
)