package com.test.spacex.di.modules

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.test.spacex.utils.Consts
import com.test.spacex.utils.NetworkUtils
import com.test.spacex.SpaceXApplication
import com.test.spacex.data.DataManager
import com.test.spacex.data.DataManagerImpl
import com.test.spacex.data.prefs.PrefsManager
import com.test.spacex.data.prefs.PrefsManagerImpl
import com.test.spacex.data.server.ApiClient
import com.test.spacex.data.server.ApiManager
import com.test.spacex.data.server.ApiManagerImpl
import com.test.spacex.di.annotations.AppContext
import com.test.spacex.di.annotations.CacheInfo
import com.test.spacex.di.annotations.PrefsInfo
import com.test.spacex.rx.AppSchedulerProvider
import com.test.spacex.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    @AppContext
    fun provideContext(context: SpaceXApplication): Context {
        return context
    }

    @Singleton
    @Provides
    fun provideSchedulers(): SchedulerProvider {
        return AppSchedulerProvider()
    }

//    @Singleton
//    @Provides
//    fun provideNetworkUtils(): NetworkUtils {
//        return NetworkUtils()
//    }


    @Singleton
    @Provides
    @PrefsInfo
    fun providePrefsName(): String {
        return Consts.PREF_NAME
    }

    @Singleton
    @Provides
    fun provideSharedPrefs(@AppContext context: Context, @PrefsInfo name: String): SharedPreferences {
        return context.getSharedPreferences(name, Context.MODE_PRIVATE)
    }

    @Singleton
    @Provides
    fun providePrefManager(sharedPreferences: SharedPreferences): PrefsManager {
        return PrefsManagerImpl(sharedPreferences)
    }


    @Singleton
    @Provides
    fun provideDataManager(dataManager: DataManagerImpl): DataManager {
        return dataManager
    }


    @Singleton
    @Provides
    fun provideApiManager(apiManager: ApiManagerImpl): ApiManager {
        return apiManager
    }

    @Provides
    @Named("non_cached")
    @Singleton
    fun provideAppApiClient(client: OkHttpClient): ApiClient {
        return Retrofit.Builder()
            .baseUrl(Consts.BASE_URL)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(ApiClient::class.java)
    }


    @Provides
    @Named("cached")
    @Singleton
    fun provideCachedAppApiClient(@CacheInfo client: OkHttpClient): ApiClient {
        return Retrofit.Builder()
            .baseUrl(Consts.BASE_URL)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(ApiClient::class.java)

    }

    @Provides
    @Singleton
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {

        return OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)// Set connection timeout
            .readTimeout(60, TimeUnit.SECONDS)// Read timeout
            .writeTimeout(60, TimeUnit.SECONDS)// Write timeout
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }


    @CacheInfo
    @Provides
    @Singleton
    fun provideOkHttpClientCached(
        @AppContext context: Context,
        @CacheInfo cache: Cache,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {

        return OkHttpClient.Builder()
            .cache(cache)// Add cache
            .connectTimeout(60, TimeUnit.SECONDS)// Set connection timeout
            .readTimeout(60, TimeUnit.SECONDS)// Read timeout
            .writeTimeout(60, TimeUnit.SECONDS)// Write timeout
            .addNetworkInterceptor(getRewriteInterceptor(context))// Add a custom cache interceptor， Note that it needs to be used here. .addNetworkInterceptor
            .addInterceptor(getRewriteInterceptor(context))
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
    }


    @Provides
    @Singleton
    @CacheInfo
    fun provideCacheDirectory(@AppContext context: Context, @CacheInfo directoryName: String): File? {
        try {
            return File(context.cacheDir, directoryName)
        } catch (e: Exception) {
            Log.e("provideCacheDirectory", "Exception", e)
        }

        return null
    }

    @Provides
    @Singleton
    @CacheInfo
    fun provideResponseCacheDirectoryName(): String {
        return Consts.HTTP_CACHE_DIRECTORY
    }


    @Provides
    @Singleton
    @CacheInfo
    fun getCache(@CacheInfo httpCacheDirectory: File?): Cache {
        val cacheSize = 10 * 1024 * 1024// Set cache file size 10M
        return Cache(httpCacheDirectory!!, cacheSize.toLong())
    }


    private fun getRewriteInterceptor(context: Context): Interceptor {


        return Interceptor { chain ->
            val originalRequest = chain.request()
            val cacheHeaderValue = if (NetworkUtils.isNetworkConnected(context))
                "public, max-age=" + Consts.HTTP_CACHE_MAX_AGE
            else
                "public, only-if-cached, max-stale=" + Consts.HTTP_CACHE_STALE_MAX_AGE
            val request = originalRequest.newBuilder().build()
            val response = chain.proceed(request)
            response.newBuilder()
                .removeHeader("Pragma")
                .removeHeader("Cache-Control")
                .header("Cache-Control", cacheHeaderValue)
                .build()
        }
    }


}