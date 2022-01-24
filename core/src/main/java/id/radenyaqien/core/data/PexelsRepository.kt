package id.radenyaqien.core.data

import androidx.paging.*
import id.radenyaqien.core.utils.Constant.ITEMS_PER_PAGE
import id.radenyaqien.core.domain.Repository
import id.radenyaqien.core.data.local.LocalDB
import id.radenyaqien.core.data.local.entity.ImageEntity
import id.radenyaqien.core.data.paging.PexelsRemoteMediator
import id.radenyaqien.core.data.remote.api.PexelsApi
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
            remoteMediator = PexelsRemoteMediator(pexelsApi, db),
            pagingSourceFactory = pagingSourceFactory
        )
        return pager.flow
    }
}