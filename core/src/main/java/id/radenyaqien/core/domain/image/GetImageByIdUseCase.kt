package id.radenyaqien.core.domain.image


import id.radenyaqien.core.domain.PexelsImage
import id.radenyaqien.core.domain.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetImageByIdUseCase @Inject constructor(
    private val repository: Repository
) {


    operator fun invoke(
        id: String
    ): Flow<PexelsImage?> = repository.getImageById(id).map {
        it?.toImageDomain()
    }
}