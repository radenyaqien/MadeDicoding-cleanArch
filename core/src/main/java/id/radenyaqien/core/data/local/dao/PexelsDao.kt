package id.radenyaqien.core.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.radenyaqien.core.data.local.entity.ImageEntity

@Dao
interface PexelsDao {

    @Query("SELECT * FROM pexels_image")
    fun getAllImages(): PagingSource<Int, ImageEntity>

    @Query("SELECT * FROM pexels_image where isFavorite = 1 ")
    fun getfavoriteImages(): PagingSource<Int, ImageEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addImages(images: List<ImageEntity>)

    @Query("DELETE FROM pexels_image")
    suspend fun deleteAllImages()
}