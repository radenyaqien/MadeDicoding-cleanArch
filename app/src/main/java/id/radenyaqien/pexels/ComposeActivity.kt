package id.radenyaqien.pexels

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import androidx.paging.ExperimentalPagingApi
import coil.annotation.ExperimentalCoilApi
import dagger.hilt.android.AndroidEntryPoint
import id.radenyaqien.pexels.navigation.SetupNavGraph
import id.radenyaqien.pexels.ui.home.HomeScreen
import id.radenyaqien.pexels.ui.theme.PexelsAppTheme

@ExperimentalPagingApi
@ExperimentalCoilApi
@AndroidEntryPoint
class ComposeActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PexelsAppTheme {
                val navHostController = rememberNavController()
                SetupNavGraph(navController = navHostController)
            }
        }
    }
}


@ExperimentalPagingApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PexelsAppTheme {
        HomeScreen(navController = rememberNavController())
    }
}