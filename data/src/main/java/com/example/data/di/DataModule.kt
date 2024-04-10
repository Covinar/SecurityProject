package com.example.data.di

import android.content.Context
import com.example.data.common.MD5Encrypt
import com.example.data.datasources.local.UsersDataSource
import com.example.data.datasources.local.UsersDataSourceImpl
import com.example.data.db.Dao
import com.example.data.db.DataBase
import com.example.data.gateways.AuthGatewayImpl
import com.example.domain.gateways.AuthGateway
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideUserDataSource(dao: Dao): UsersDataSource {
        return UsersDataSourceImpl(dao)
    }

    @Provides
    @Singleton
    fun provideUsersDao(@ApplicationContext context: Context): Dao {
        return DataBase.getDb(context).getDao()
    }

    @Provides
    @Singleton
    fun provideAuthGateway(usersDataSource: UsersDataSource, mD5Encrypt: MD5Encrypt): AuthGateway {
        return AuthGatewayImpl(usersDataSource, mD5Encrypt)
    }

    @Provides
    @Singleton
    fun provideMD5Encrypt() = MD5Encrypt()

}