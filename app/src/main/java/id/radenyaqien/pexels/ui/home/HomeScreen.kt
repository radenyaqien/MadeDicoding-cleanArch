package id.radenyaqien.pexels.ui.home

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.ExperimentalPagingApi
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import id.radenyaqien.pexels.navigation.Screen

@ExperimentalCoilApi
@ExperimentalPagingApi
@Composable
fun HomeScreen(
    navController: NavHostController,
    homeViewModel: ListViewModel = hiltViewModel()
) {
    val getAllImages = homeViewModel.data.collectAsLazyPagingItems()


    Scaffold(
        topBar = {
            HomeTopBar(
                onMenuClicked = {
                    navController.navigate(Screen.Favorite.route)
                }
            )
        },
        content = {
            ListContent(
                list = getAllImages,
                navHostController = navController
            )
        }
    )
}
