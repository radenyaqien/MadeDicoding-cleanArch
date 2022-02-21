package id.radenyaqien.core.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import id.radenyaqien.core.data.local.LocalDB
import id.radenyaqien.core.data.remote.api.PexelsApi
import id.radenyaqien.core.utils.Constant.ITEMS_PER_PAGE
import id.radenyaqien.core.data.local.dao.PexelsDao
import id.radenyaqien.core.data.local.dao.PexelsRemoteKeysDao
import id.radenyaqien.core.data.local.entity.ImageEntity
import id.radenyaqien.core.data.local.entity.PexelsRemoteKeys
import javax.inject.Inject

@ExperimentalPagingApi
class PexelsRemoteMediator @Inject constructor(
    private val pexelsApi: PexelsApi,
    private val db: LocalDB
) : RemoteMediator<Int, ImageEntity>() {

    private val pexelsDao: PexelsDao = db.pexelsDao()
    private val remoteDao: PexelsRemoteKeysDao = db.remotekeysDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ImageEntity>
    ): MediatorResult {

        val currentPage = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextPage?.minus(1) ?: 1
            }
            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                val prevPage = remoteKeys?.prevPage
                    ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                prevPage
            }
            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                val nextPage = remoteKeys?.nextPage
                    ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                nextPage
            }
        }
        try {
            val response = pexelsApi.getAllImages(page = currentPage, perPage = ITEMS_PER_PAGE)
            val endOfPaginationReached = response.data.isEmpty()

            val prevPage = if (currentPage == 1) null else currentPage - 1
            val nextPage = if (endOfPaginationReached) null else currentPage + 1

            db.withTransaction {
//                if (loadType == LoadType.REFRESH) {
//                    remoteDao.deleteAllRemoteKeys()
//                    pexelsDao.deleteAllImages()
//                }
                val keys = response.data.map { pexels ->
                    PexelsRemoteKeys(
                        id = pexels.id.toString(),
                        prevPage = prevPage,
                        nextPage = nextPage
                    )
                }
                remoteDao.addAllRemoteKeys(remoteKeys = keys)
                pexelsDao.addImages(images = response.data.map {
                    it.toImageEntity()
                })
            }
            return MediatorResult.Success(endOfPaginationReached)
        } catch (e: Exception) {

            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, ImageEntity>
    ): PexelsRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                remoteDao.getRemoteKeys(id = id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, ImageEntity>
    ): PexelsRemoteKeys? {
        return state.firstItemOrNull()
            ?.let { img ->
                remoteDao.getRemoteKeys(id = img.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, ImageEntity>
    ): PexelsRemoteKeys? {
        return state.lastItemOrNull()?.let { img ->
            remoteDao.getRemoteKeys(id = img.id)
        }
    }


}