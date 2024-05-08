package com.capitalone.mobile.onelist.data.user.fakeImpl.di

import com.capitalone.mobile.onelist.data.user.api.UserRepository
import com.capitalone.mobile.onelist.data.user.fakeImpl.FakeUserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class Module {
    @Binds
    @Singleton
    abstract fun bindFakeUserRepository(fakeUserRepository: FakeUserRepository): UserRepository
}