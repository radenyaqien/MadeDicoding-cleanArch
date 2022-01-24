package id.radenyaqien.core.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.radenyaqien.core.data.local.entity.PexelsRemoteKeys

@Dao
interface PexelsRemoteKeysDao {

    @Query("SELECT * FROM remote_keys WHERE id =:id")
    suspend fun getRemoteKeys(id: String): PexelsRemoteKeys

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(remoteKeys: List<PexelsRemoteKeys>)

    @Query("DELETE FROM remote_keys")
    suspend fun deleteAllRemoteKeys()

}