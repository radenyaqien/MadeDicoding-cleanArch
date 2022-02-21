package id.radenyaqien.dynamicfeature

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.findNavController
import dagger.hilt.android.EntryPointAccessors
import id.radenyaqien.core.domain.Repository
import id.radenyaqien.core.domain.favorite.GetImageFavoriteUseCase
import id.radenyaqien.pexels.DfmDependencies
import id.radenyaqien.pexels.R
import id.radenyaqien.pexels.ui.imagedetail.DetailFragment
import id.radenyaqien.pexels.ui.theme.PexelsAppTheme
import javax.inject.Inject


class FavoriteFragment : Fragment() {

    @Inject
    lateinit var repo: Repository
    private val usecase by lazy { GetImageFavoriteUseCase(repository = repo) }
    private val factory by lazy { VmProvider(usecase = usecase) }
    private val viewModel: FavoriteViewModel by viewModels {
        factory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerFavoriteComponent.factory()
            .create(
                EntryPointAccessors.fromApplication(
                    requireActivity().applicationContext, DfmDependencies::class.java
                )
            ).inject(this)

        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                PexelsAppTheme {
                    val lifecycleOwner = LocalLifecycleOwner.current
                    val locationFlowLifecycleAware =
                        remember(viewModel.fav, lifecycleOwner) {
                            viewModel.fav.flowWithLifecycle(
                                lifecycleOwner.lifecycle,
                                Lifecycle.State.STARTED
                            )
                        }

                    val images by locationFlowLifecycleAware.collectAsState(emptyList())
                    FavoriteScreen(list = images, onclickItem = {
                        findNavController().navigate(
                            R.id.action_navigation_favorite_to_navigation_detail,
                            Bundle().apply
                            {
                                putString(DetailFragment.ARGS_PEXELSID, it)
                            })
                    })

                }
            }

        }
    }


}