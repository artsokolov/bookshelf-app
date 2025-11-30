package com.example.bookshelfapp.ui.nav

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.bookshelfapp.ui.screens.BookDetailsViewModel
import com.example.bookshelfapp.ui.screens.BookShelfViewModel
import com.example.bookshelfapp.ui.screens.DetailsScreen
import com.example.bookshelfapp.ui.screens.HomeScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    bookShelfModel: BookShelfViewModel,
    contentPadding: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = Route.Home.route
    ) {
        composable(route = Route.Home.route) {
            HomeScreen(
                bookShelfUiState = bookShelfModel.booksShelfUiState,
                onBookClick = { id ->
                    navController.navigate(Route.BookDetails.createRoute(id))
                },
                modifier = Modifier.fillMaxSize(),
                contentPadding = contentPadding
            )
        }

        composable(
            route = Route.BookDetails.route,
            arguments = listOf(navArgument("bookId") { type = NavType.StringType })
        ) { backStackEntry ->
            val bookId = backStackEntry.arguments?.getString("bookId")!!

            val viewModel: BookDetailsViewModel = viewModel(
                factory = BookDetailsViewModel.provideFactory(
                    bookId = bookId
                )
            )

            DetailsScreen(
                contentPadding = contentPadding,
                bookDetailsUiState = viewModel.bookDetailsUiState
            )
        }
    }
}