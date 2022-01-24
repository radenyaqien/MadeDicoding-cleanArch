package id.radenyaqien.pexels.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import id.radenyaqien.pexels.domain.Image
import id.radenyaqien.pexels.domain.getImageUsecase
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val usecase: getImageUsecase
) : ViewModel() {

//    // FIXME: 22/01/22
//    private val _data : MutableStateFlow<DataState> = MutableStateFlow(DataState.isLoading())
//    val data : StateFlow<DataState> get() = _data.asStateFlow()
//    fun loadImage()  {
//        usecase.invoke().onEach { pag->
//           _data.value = _data.getAndUpdate {
//               when(it){
//                   is DataState.isLoading -> it.copy(true)
//                   is DataState.imageList -> it.copy(pag)
//               }
//           }
//        }.launchIn(viewModelScope)
//    }

    fun image() = usecase.invoke()

    sealed class DataState{
        data class imageList(val pagin : PagingData<Image>) : DataState() 
        data class isLoading(val isloading : Boolean =false) : DataState()
    }


}