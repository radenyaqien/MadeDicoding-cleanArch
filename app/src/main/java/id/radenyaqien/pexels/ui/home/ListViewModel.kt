package id.radenyaqien.pexels.ui.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.radenyaqien.core.domain.image.GetImageUsecase
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val usecase: GetImageUsecase
) : ViewModel() {


    val data = usecase.invoke()
//    private val _data : MutableStateFlow<State> = MutableStateFlow(State())
//    val data : StateFlow<State> get() = _data.asStateFlow()
//    private fun loadImage()  {
//        usecase.invoke().onEach { pag->
//           _data.value = _data.updateAndGet{
//               it.copy(pagin = pag)
//           }
//        }.launchIn(viewModelScope)
//    }


//    data class State(
//        val pagin : PagingData<Image>? = null,
//        val isloading : Boolean =false
//    )

//    init {
//        loadImage()
//    }

}