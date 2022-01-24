package id.radenyaqien.pexels.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import id.radenyaqien.pexels.domain.Image
import id.radenyaqien.pexels.utils.Constant

@Entity(tableName = Constant.PEXELS_IMAGE)
data class ImageEntity(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "photographer")
    val photographer: String?,
    @ColumnInfo(name = "photographerUrl")
    val photographerUrl: String?,
    @ColumnInfo(name = "url")
    val url: String?,
    @ColumnInfo(name = "src")
    val src: String?,
    @ColumnInfo(name = "alt")
    val alt: String?,
    @ColumnInfo(name = "isFavorite")
    val isFavorite: Boolean = false,
){
    fun toImageDomain() : Image = Image(
        id, photographer, photographerUrl, src, url, alt,isFavorite
    )

}