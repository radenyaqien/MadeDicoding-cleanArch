package id.radenyaqien.dynamicfeature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.radenyaqien.core.domain.PexelsImage
import id.radenyaqien.core.domain.favorite.GetImageFavoriteUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class FavoriteViewModel(
    private val usecase: GetImageFavoriteUseCase
) : ViewModel() {

    private val _fav: MutableStateFlow<List<PexelsImage>> = MutableStateFlow(emptyList())
    val fav get() = _fav.asStateFlow()


    private fun getfav() = usecase.invoke().onEach {
        _fav.value = it
    }.launchIn(viewModelScope)

    init {
        getfav()
    }

}