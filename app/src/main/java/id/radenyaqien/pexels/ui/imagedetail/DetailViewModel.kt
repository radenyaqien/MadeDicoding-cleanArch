package id.radenyaqien.pexels.ui.imagedetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.radenyaqien.core.domain.image.GetImageByIdUseCase
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val usecase: GetImageByIdUseCase,

    savedStateHandle: SavedStateHandle
) : ViewModel() {

    fun imageDetail(id: String) = usecase.invoke(id)


}