package id.radenyaqien.dynamicfeature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.radenyaqien.core.domain.favorite.GetImageFavoriteUseCase

@Suppress("UNCHECKED_CAST")
class VmProvider(private val usecase: GetImageFavoriteUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FavoriteViewModel(usecase = usecase) as T
    }
}