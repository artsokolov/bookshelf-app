package com.example.bookshelfapp.data

fun String.ensureHttps() = this.replace("http://", "https://")