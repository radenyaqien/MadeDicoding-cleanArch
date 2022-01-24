package id.radenyaqien.pexels.domain

import androidx.paging.PagingData
import id.radenyaqien.pexels.data.local.entity.ImageEntity
import kotlinx.coroutines.flow.Flow

interface Repository {

    fun getAllImages() : Flow<PagingData<ImageEntity>>
}