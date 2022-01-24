package id.radenyaqien.core.domain

import androidx.paging.PagingData
import androidx.paging.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetImageUsecase @Inject constructor(
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