package id.radenyaqien.pexels.ui.imagedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import id.radenyaqien.pexels.ui.theme.PexelsAppTheme


@AndroidEntryPoint
class DetailFragment : Fragment() {
    private var idku: String? = null
    private val viewModel: DetailViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        idku = arguments?.getString(ARGS_PEXELSID)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val pexels by viewModel.imageDetail(idku ?: "").collectAsState(initial = null)

                PexelsAppTheme {
                    PexelsDetail(pexels = pexels) {
                        pexels?.let { it1 -> viewModel.setFavorite(it, it1) }
                    }
                }
            }

        }
    }

    companion object {
        const val ARGS_PEXELSID = "kjsdkjbasjkdb"
    }

}