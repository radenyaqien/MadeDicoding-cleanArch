package id.radenyaqien.pexels.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.paging.compose.collectAsLazyPagingItems
import dagger.hilt.android.AndroidEntryPoint
import id.radenyaqien.pexels.R
import id.radenyaqien.pexels.ui.imagedetail.DetailFragment
import id.radenyaqien.pexels.ui.theme.PexelsAppTheme

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val viewModel: HomeViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return ComposeView(requireContext()).apply {

            setContent {
                PexelsAppTheme {
                    val data = viewModel.data.collectAsLazyPagingItems()
                    ListContent(list = data) {
                        findNavController().navigate(
                            R.id.action_navigation_home_to_navigation_detail,
                            Bundle().apply
                            {
                                putString(DetailFragment.ARGS_PEXELSID, it)
                            })

                    }
                }
            }


        }
    }

}