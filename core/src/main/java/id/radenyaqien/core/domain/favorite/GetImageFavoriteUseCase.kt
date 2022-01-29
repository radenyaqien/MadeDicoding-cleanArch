package id.radenyaqien.core.domain.favorite

import id.radenyaqien.core.domain.PexelsImage
import id.radenyaqien.core.domain.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetImageFavoriteUseCase @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(): Flow<List<PexelsImage>> = repository.getFavoriteImage().map {
        it.map {
            it.toImageDomain()
        }
    }
}