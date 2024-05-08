package com.capitalone.mobile.onelist.data.todo.fakeimpl.di

import com.capitalone.mobile.onelist.data.todo.api.TodoRepository
import com.capitalone.mobile.onelist.data.todo.fakeimpl.FakeTodoRepository
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
    abstract fun bindFakeTodoRepository(fakeTodoRepository: FakeTodoRepository): TodoRepository
}