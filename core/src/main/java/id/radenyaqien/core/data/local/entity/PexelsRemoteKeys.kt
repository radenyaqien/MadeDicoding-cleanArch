package id.radenyaqien.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import id.radenyaqien.core.utils.Constant.PEXELS_REMOTE_KEYS_TABLE

@Entity(tableName = PEXELS_REMOTE_KEYS_TABLE)
data class PexelsRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val prevPage: Int?,
    val nextPage: Int?
)