package com.blanc.data.di

import com.blanc.data.remote.DiaryRemoteDataSource
import com.blanc.data.remote.DiaryRemoteDataSourceImpl
import com.blanc.data.remote.UserRemoteDataSource
import com.blanc.data.remote.UserRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteModule {

    @Singleton
    @Binds
    abstract fun bindUserRemoteDataSource(
        userRemoteDataSourceImpl: UserRemoteDataSourceImpl
    ): UserRemoteDataSource

    @Singleton
    @Binds
    abstract fun bindDiaryRemoteDataSource(
        diaryRemoteDataSourceImpl: DiaryRemoteDataSourceImpl
    ): DiaryRemoteDataSource

}
