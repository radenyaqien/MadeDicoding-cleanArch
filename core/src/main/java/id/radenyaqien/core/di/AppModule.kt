package id.radenyaqien.core.di


import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import id.radenyaqien.core.BuildConfig
import id.radenyaqien.core.data.PexelsRepository
import id.radenyaqien.core.data.local.LocalDB
import id.radenyaqien.core.data.remote.api.PexelsApi
import id.radenyaqien.core.domain.Repository
import id.radenyaqien.core.utils.Constant
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideClient(): OkHttpClient {
        val hostname = "www.pexels.com"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/t5uUD8NoY8LBKCTw7ojA6qE3slJ001VHL3Sa+dUXnxk=")
            .add(hostname, "sha256/t5uUD8NoY8LBKCTw7ojA6qE3slJ001VHL3Sa+dUXnxk=")
            .add(hostname, "sha256/t5uUD8NoY8LBKCTw7ojA6qE3slJ001VHL3Sa+dUXnxk=")
            .add(hostname, "sha256/t5uUD8NoY8LBKCTw7ojA6qE3slJ001VHL3Sa+dUXnxk=")
            .build()
        return OkHttpClient().newBuilder().also { client ->
            if (BuildConfig.DEBUG) {
                val loggingInterceptor = HttpLoggingInterceptor()
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
                client.addInterceptor(loggingInterceptor)
                    .certificatePinner(certificatePinner)
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

            client.connectTimeout(5, TimeUnit.MINUTES) // connect timeout
                .writeTimeout(5, TimeUnit.MINUTES) // write timeout
                .readTimeout(5, TimeUnit.MINUTES)
                .certificatePinner(certificatePinner)
     }.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient) : Retrofit{
        return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) : LocalDB {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("mainulyaqin".toCharArray())
        val factory = SupportFactory(passphrase)
        return Room.databaseBuilder(
            context.applicationContext,
            LocalDB::class.java,
            Constant.DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }

    @ExperimentalPagingApi
    @Provides
    @Singleton
    fun provideRepository(pexelsApi: PexelsApi, localDB: LocalDB) : Repository {
        return PexelsRepository(pexelsApi,localDB)
    }

}