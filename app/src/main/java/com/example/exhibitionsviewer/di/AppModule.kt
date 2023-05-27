package com.example.exhibitionsviewer.di

import android.content.Context
import androidx.room.Room
import com.example.exhibitionsviewer.data.api.ApiKeyInterceptor
import com.example.exhibitionsviewer.data.api.ApiService
import com.example.exhibitionsviewer.data.dao.MainDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) //dependencies provided here will be used across the application
class AppModule {

    @Provides
    @Named("BASE_URL")
    fun provideBaseUrl(): String = "http://exhibions-env.eba-vrdxc7uk.eu-west-3.elasticbeanstalk.com/"

    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor: Interceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

    @Provides
    fun provideInterceptor(): Interceptor = ApiKeyInterceptor()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, @Named("BASE_URL") BASE_URL: String): Retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideViewedDB(@ApplicationContext ctx: Context): MainDatabase = Room
        .databaseBuilder(ctx.applicationContext, MainDatabase::class.java, "exhibitions_database")
        .fallbackToDestructiveMigration()
        .build()

}