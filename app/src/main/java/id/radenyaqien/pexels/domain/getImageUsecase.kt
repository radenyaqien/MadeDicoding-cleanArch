package id.radenyaqien.pexels.domain

import androidx.paging.PagingData
import androidx.paging.map
import id.radenyaqien.pexels.data.local.entity.ImageEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class getImageUsecase @Inject constructor(
    private val repository: Repository
) {

    operator fun invoke(): Flow<PagingData<Image>> {

        return repository.getAllImages().map {
            it.map {
                it.toImageDomain()
            }
        }
    }
}