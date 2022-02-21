package id.radenyaqien.pexels.ui.imagedetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.radenyaqien.core.domain.PexelsImage
import id.radenyaqien.core.domain.favorite.DeletefavoriteUsecase
import id.radenyaqien.core.domain.favorite.SetFavoriteUsecase
import id.radenyaqien.core.domain.image.GetImageByIdUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val usecase: GetImageByIdUseCase,
    private val setfavorite: SetFavoriteUsecase,
    private val delFavorite: DeletefavoriteUsecase
) : ViewModel() {

    fun imageDetail(id: String) = usecase.invoke(id)

    fun setFavorite(boolean: Boolean, pexelsImage: PexelsImage) = viewModelScope.launch {

        if (boolean) {
            delFavorite.invoke(pexelsImage = pexelsImage)
        } else {
            setfavorite.invoke(pexelsImage)
        }


    }

}