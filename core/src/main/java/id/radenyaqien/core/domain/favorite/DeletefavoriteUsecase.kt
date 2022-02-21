package id.radenyaqien.core.domain.favorite

import id.radenyaqien.core.domain.PexelsImage
import id.radenyaqien.core.domain.Repository
import javax.inject.Inject

class DeletefavoriteUsecase @Inject constructor(
    private val repository: Repository
)  {

    suspend operator fun invoke(pexelsImage: PexelsImage) = repository.deleteFavorite(model =pexelsImage.toFavoriteEntity() )
}