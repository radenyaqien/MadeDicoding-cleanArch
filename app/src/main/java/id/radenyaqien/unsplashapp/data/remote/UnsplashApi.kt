package id.radenyaqien.unsplashapp.data.remote


import id.radenyaqien.capstonedicoding.data.local.entity.ImageEntity
import id.radenyaqien.unsplashapp.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface UnsplashApi {

    @Headers("Authorization: Client-ID ${BuildConfig.API_KEY}")
    @GET("/photos")
    suspend fun getAllImages(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): List<ImageEntity>
}