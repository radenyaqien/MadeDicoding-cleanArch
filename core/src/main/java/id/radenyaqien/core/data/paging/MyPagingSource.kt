package id.radenyaqien.core.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import id.radenyaqien.core.data.remote.api.PexelsApi
import id.radenyaqien.core.data.local.entity.ImageEntity
import id.radenyaqien.core.utils.Constant.ITEMS_PER_PAGE
import javax.inject.Inject

class MyPagingSource @Inject constructor(private val api : PexelsApi) : PagingSource<Int, ImageEntity>() {
    override fun getRefreshKey(state: PagingState<Int, ImageEntity>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ImageEntity> {
        val currentPage = params.key ?: 1
        return try {
            val response = api.getAllImages(currentPage,ITEMS_PER_PAGE)
            val endOfPaginationReached = response.data.isEmpty()
            if (response.data.isNotEmpty()) {
                LoadResult.Page(
                    data = response.data.map { it.toImageEntity() },
                    prevKey = if (currentPage == 1) null else currentPage - 1,
                    nextKey = if (endOfPaginationReached) null else currentPage + 1
                )
            } else {
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}