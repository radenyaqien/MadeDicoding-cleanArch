package id.radenyaqien.pexels.ui.imagedetail

import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.produceState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import id.radenyaqien.core.domain.PexelsImage
import kotlinx.coroutines.flow.Flow

@Composable
fun PexelsDetail(
    pexelsId : String,
    navController: NavController,
    viewModel: DetailViewModel = hiltViewModel()) {

}