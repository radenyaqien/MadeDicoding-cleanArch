package id.radenyaqien.pexels.domain

import androidx.paging.PagingData
import id.radenyaqien.pexels.data.local.entity.ImageEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class getImageUsecase @Inject constructor(
    private val repository: Repository
) {

    operator fun invoke(): Flow<PagingData<ImageEntity>> {

        return repository.getAllImages()
    }
}