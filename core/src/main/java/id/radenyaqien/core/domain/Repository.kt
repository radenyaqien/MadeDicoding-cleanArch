package id.radenyaqien.core.domain

import androidx.paging.PagingData
import id.radenyaqien.core.data.local.entity.ImageEntity
import kotlinx.coroutines.flow.Flow

interface Repository {

    fun getAllImages() : Flow<PagingData<ImageEntity>>

    fun getImageById(id :String) : Flow<ImageEntity?>

    fun getFavoriteImage() : Flow<List<ImageEntity>>
}