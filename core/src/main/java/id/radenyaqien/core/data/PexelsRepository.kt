package id.radenyaqien.core.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import id.radenyaqien.core.data.local.LocalDB
import id.radenyaqien.core.data.local.entity.ImageEntity
import id.radenyaqien.core.data.paging.PexelsRemoteMediator
import id.radenyaqien.core.data.remote.api.PexelsApi
import id.radenyaqien.core.domain.Repository
import id.radenyaqien.core.utils.Constant.ITEMS_PER_PAGE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ExperimentalPagingApi
class PexelsRepository @Inject constructor(
    private val pexelsApi: PexelsApi,
    private val db: LocalDB
) : Repository {

    override fun getAllImages(): Flow<PagingData<ImageEntity>> {
        val pagingSourceFactory = { db.pexelsDao().getAllImages() }
        val pager = Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            pagingSourceFactory = pagingSourceFactory,
            remoteMediator = PexelsRemoteMediator(pexelsApi, db)
        )
        return pager.flow
    }

    override fun getImageById(id: String): Flow<ImageEntity?> {

        return db.pexelsDao().getImageById(id)
    }


    override fun getFavoriteImage(): Flow<List<ImageEntity>> {
        return db.pexelsDao().getAllImagesFav()
    }


    override suspend fun insertFavorite(model: ImageEntity) {
        db.pexelsDao().updateFavoriteById(true, model.id)
    }


    override suspend fun deleteFavorite(model: ImageEntity) {
        db.pexelsDao().updateFavoriteById(false, model.id)
    }


}