package com.example.bookshelfapp.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.bookshelfapp.R
import com.example.bookshelfapp.ui.components.BookShelfTopAppBar
import com.example.bookshelfapp.ui.nav.NavGraph
import com.example.bookshelfapp.ui.nav.Route
import com.example.bookshelfapp.ui.screens.BookDetailsViewModel
import com.example.bookshelfapp.ui.screens.BookShelfViewModel
import com.example.bookshelfapp.ui.screens.DetailsScreen
import com.example.bookshelfapp.ui.screens.HomeScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookShelfApp() {
    val navController: NavHostController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val bookShelfModel: BookShelfViewModel = viewModel(factory = BookShelfViewModel.factory)

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            BookShelfTopAppBar(
                canNavigateBack = currentRoute != Route.Home.route,
                navigateUp = {
                    navController.navigateUp()
                },
                scrollBehavior = scrollBehavior
            )
        }
    ) { innerPadding ->
        NavGraph(
            navController = navController,
            bookShelfModel = bookShelfModel,
            contentPadding = innerPadding
        )
    }
}