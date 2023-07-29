package com.blanc.data.di

import com.blanc.data.repository.DiaryRepositoryImpl
import com.blanc.data.repository.UserRepositoryImpl
import com.blanc.domain.user.repository.DiaryRepository
import com.blanc.domain.user.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository

    @Singleton
    @Binds
    abstract fun bindDiaryRepository(
        diaryRepositoryImpl: DiaryRepositoryImpl
    ): DiaryRepository

}