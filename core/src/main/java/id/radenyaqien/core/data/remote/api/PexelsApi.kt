package id.radenyaqien.core.data.remote.api


import id.radenyaqien.core.BuildConfig
import id.radenyaqien.core.data.remote.response.MyResponse

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface PexelsApi {

    @Headers("Authorization: ${BuildConfig.API_KEY}")
    @GET("curated")
    suspend fun getAllImages(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): MyResponse

}