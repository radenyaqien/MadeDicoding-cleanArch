package id.radenyaqien.core.di


import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import id.radenyaqien.core.*
import id.radenyaqien.core.data.PexelsRepository
import id.radenyaqien.core.domain.Repository
import id.radenyaqien.core.data.local.LocalDB
import id.radenyaqien.core.data.remote.api.PexelsApi
import id.radenyaqien.core.utils.Constant


import okhttp3.CipherSuite
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import okhttp3.TlsVersion
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideClient() : OkHttpClient{
     return  OkHttpClient().newBuilder().also { client ->
            if (BuildConfig.DEBUG) {
                val loggingInterceptor = HttpLoggingInterceptor()
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
                client.addInterceptor(loggingInterceptor)
            } else {
                val spec: ConnectionSpec = ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                    .tlsVersions(TlsVersion.TLS_1_2)
                    .cipherSuites(
                        CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
                        CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
                        CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256
                    )
                    .build()
                client.connectionSpecs(listOf(spec))
            }
        }.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient) : Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://api.pexels.com/v1/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) : LocalDB {
        return Room.databaseBuilder(
            context.applicationContext,
            LocalDB::class.java,
            Constant.DATABASE_NAME
        ).build()
    }
//    @Provides
//    @Singleton
//    fun provideDao(db: LocalDB) : PexelsDao {
//        return db.pexelsDao()
//    }
//    @Provides
//    @Singleton
//    fun provideRemoteKeysDao(db: LocalDB) : PexelsRemoteKeysDao {
//        return db.remotekeysDao()
//    }

    @ExperimentalPagingApi
    @Provides
    @Singleton
    fun provideRepository(pexelsApi: PexelsApi, localDB: LocalDB) : Repository {
        return PexelsRepository(pexelsApi,localDB)
    }

}