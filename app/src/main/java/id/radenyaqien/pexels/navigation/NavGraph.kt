package id.radenyaqien.pexels.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.paging.ExperimentalPagingApi
import coil.annotation.ExperimentalCoilApi
import id.radenyaqien.pexels.ui.favorite.FavoriteScreen
import id.radenyaqien.pexels.ui.home.HomeScreen
import id.radenyaqien.pexels.ui.imagedetail.PexelsDetail

@ExperimentalCoilApi
@ExperimentalPagingApi
@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {

        composable(route = Screen.Home.route){
            HomeScreen(navController = navController)
        }
        composable(route = Screen.Detail.route + "/{pexelsid}",
            arguments = listOf(
                navArgument("pexelsid") {
                    type = NavType.StringType
                }
            )){
            val pexelsID = remember {
                it.arguments?.getString("pexelsid")}
            if (pexelsID != null) {
                PexelsDetail(
                    navController = navController,
                    pexelsId = pexelsID
                )
            }
        }
        composable(route = Screen.Favorite.route){
            FavoriteScreen(navController = navController)
        }
    }
}