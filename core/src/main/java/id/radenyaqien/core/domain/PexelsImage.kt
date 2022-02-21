package id.radenyaqien.core.domain

import id.radenyaqien.core.data.local.entity.ImageEntity

data class PexelsImage(
    val id: String,
    val photographer: String?,
    val photographerUrl: String?,
    val src: String?,
    val url: String?,
    val alt: String?,
    val isfavorite: Boolean = false
) {
    fun toFavoriteEntity() =
        ImageEntity(id, photographer, photographerUrl, url, src, alt, isfavorite)

}