package com.blanc.data.di

import com.blanc.data.service.DiaryApi
import com.blanc.data.service.ShareTodayApi
import com.blanc.data.service.UserApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object ApiModule {

    private const val LEGACY_BASE_URL = "http://share-today.duckdns.org"

    private const val BASE_URL = "https://share-today.site"

    private fun getDefaultRetrofit(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun provideShareTodayApi(): ShareTodayApi {
        return getDefaultRetrofit()
            .create(ShareTodayApi::class.java)
    }

    @Singleton
    @Provides
    fun provideUserApi(): UserApi {
        return getDefaultRetrofit()
            .create(UserApi::class.java)
    }

    @Singleton
    @Provides
    fun provideDiaryApi(): DiaryApi {
        return getDefaultRetrofit()
            .create(DiaryApi::class.java)
    }
}