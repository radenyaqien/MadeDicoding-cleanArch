package id.radenyaqien.pexels.ui.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.radenyaqien.core.domain.image.GetImageUsecase
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    usecase: GetImageUsecase
) : ViewModel() {

    val data = usecase.invoke()

}