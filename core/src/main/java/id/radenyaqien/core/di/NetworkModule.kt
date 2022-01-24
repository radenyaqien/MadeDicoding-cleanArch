package id.radenyaqien.core.di


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.radenyaqien.core.data.remote.api.PexelsApi
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideUnsplashInterface(retrofit: Retrofit): PexelsApi {
        return retrofit.create(PexelsApi::class.java)
    }


}