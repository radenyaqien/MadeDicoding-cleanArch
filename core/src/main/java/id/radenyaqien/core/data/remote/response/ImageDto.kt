package id.radenyaqien.core.data.remote.response


import com.google.gson.annotations.SerializedName
import id.radenyaqien.core.data.local.entity.ImageEntity

data class ImageDto(
    @SerializedName("alt")
    val alt: String,
    @SerializedName("avg_color")
    val avgColor: String,
    @SerializedName("height")
    val height: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("liked")
    val liked: Boolean,
    @SerializedName("photographer")
    val photographer: String,
    @SerializedName("photographer_id")
    val photographerId: Int,
    @SerializedName("photographer_url")
    val photographerUrl: String,
    @SerializedName("src")
    val src: Src,
    @SerializedName("url")
    val url: String,
    @SerializedName("width")
    val width: Int
) {
    fun toImageEntity() : ImageEntity {

        return ImageEntity(
            id.toString(),
            photographer,
            photographerUrl,
            url,
            src.medium,
            alt
        )
    }

}