package id.radenyaqien.core.domain.favorite

import id.radenyaqien.core.domain.PexelsImage
import id.radenyaqien.core.domain.Repository
import javax.inject.Inject

class SetFavoriteUsecase @Inject constructor(
    private val repository: Repository
) {

    suspend operator fun invoke(pexelsImage: PexelsImage) = repository.insertFavorite(pexelsImage.toFavoriteEntity())

}