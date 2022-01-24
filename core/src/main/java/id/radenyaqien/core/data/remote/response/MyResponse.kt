package id.radenyaqien.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class MyResponse(
    @SerializedName("photos")
    val data: List<ImageDto>
)
