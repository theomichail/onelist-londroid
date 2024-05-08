package com.capitalone.mobile.onelist.data.todo.impl.di

import com.capitalone.mobile.onelist.data.todo.api.TodoRepository
import com.capitalone.mobile.onelist.data.todo.impl.NetworkTodoRepository
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
    abstract fun bindNetworkTodoRepository(networkTodoRepository: NetworkTodoRepository): TodoRepository
}