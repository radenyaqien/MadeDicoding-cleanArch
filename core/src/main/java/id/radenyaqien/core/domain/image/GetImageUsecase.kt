package id.radenyaqien.core.domain.image

import androidx.paging.PagingData
import androidx.paging.map
import id.radenyaqien.core.domain.PexelsImage
import id.radenyaqien.core.domain.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetImageUsecase @Inject constructor(
    private val repository: Repository
) {

    operator fun invoke(): Flow<PagingData<PexelsImage>> {

        return repository.getAllImages().map { data ->
            data.map {
                it.toImageDomain()
            }
        }
    }
}