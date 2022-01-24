package id.radenyaqien.pexels.data

import androidx.paging.*
import id.radenyaqien.pexels.data.local.LocalDB
import id.radenyaqien.pexels.data.local.entity.ImageEntity
import id.radenyaqien.pexels.data.local.paging.PexelsRemoteMediator
import id.radenyaqien.pexels.data.remote.PexelsApi
import id.radenyaqien.pexels.domain.Repository
import id.radenyaqien.pexels.utils.Constant.ITEMS_PER_PAGE
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