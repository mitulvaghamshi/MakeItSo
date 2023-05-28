package me.mitul.todo.service.module

import me.mitul.todo.service.impl.AccountServiceImpl
import me.mitul.todo.service.impl.ConfigurationServiceImpl
import me.mitul.todo.service.impl.LogServiceImpl
import me.mitul.todo.service.impl.StorageServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.mitul.todo.service.AccountService
import me.mitul.todo.service.ConfigurationService
import me.mitul.todo.service.LogService
import me.mitul.todo.service.StorageService

@Module
@InstallIn(SingletonComponent::class)
abstract class ServiceModule {
    @Binds
    abstract fun provideAccountService(impl: AccountServiceImpl): AccountService

    @Binds
    abstract fun provideLogService(impl: LogServiceImpl): LogService

    @Binds
    abstract fun provideStorageService(impl: StorageServiceImpl): StorageService

    @Binds
    abstract fun provideConfigurationService(impl: ConfigurationServiceImpl): ConfigurationService
}
