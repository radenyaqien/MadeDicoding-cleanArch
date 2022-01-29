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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
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
            remoteMediator = PexelsRemoteMediator(pexelsApi, db),
            pagingSourceFactory = pagingSourceFactory
        )
        return pager.flow
    }

    override fun getImageById(id: String) = flow {
        emit(db.pexelsDao().getImageById(id))
    }

    override fun getFavoriteImage() = flow {
        val data = db.pexelsDao().getfavoriteImages()
        Timber.d("getFavoriteImage: $data")
        emitAll(data)
    }.flowOn(Dispatchers.IO)


}