package id.radenyaqien.core.domain

data class PexelsImage(
    val id: String,
    val photographer: String?,
    val photographerUrl: String?,
    val src: String?,
    val url: String?,
    val alt: String?,
    val isfavorite : Boolean = false
)