package com.example.bookshelfapp.ui.nav

sealed class Route(val route: String) {
    data object Home : Route("home")

    data object BookDetails : Route("details/{bookId}") {
        fun createRoute(bookId: String) = "details/$bookId"
    }
}
