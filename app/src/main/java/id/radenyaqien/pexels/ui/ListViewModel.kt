package id.radenyaqien.pexels.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import id.radenyaqien.pexels.domain.Image
import id.radenyaqien.pexels.domain.getImageUsecase
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val usecase: getImageUsecase
) : ViewModel() {


    private val _data : MutableStateFlow<State> = MutableStateFlow(State())
    val data : StateFlow<State> get() = _data.asStateFlow()
    private fun loadImage()  {
        usecase.invoke().onEach { pag->
           _data.value = _data.updateAndGet {
             it.copy(pagin = pag)
           }
        }.launchIn(viewModelScope)
    }


    data class State(
        val pagin : PagingData<Image>? = null,
        val isloading : Boolean =false
    )

    init {
        loadImage()
    }

}