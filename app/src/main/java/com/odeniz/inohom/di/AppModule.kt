package com.odeniz.inohom.di

import android.content.Context
import android.content.res.Resources
import com.odeniz.inohom.manager.WebSocketManager
import com.odeniz.inohom.repository.AuthRepository
import com.odeniz.inohom.repository.ControlRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideWebSocketManager(): WebSocketManager {
        return WebSocketManager
    }

    @Provides
    @Singleton
    fun provideAuthRepository(webSocketManager: WebSocketManager): AuthRepository {
        return AuthRepository(webSocketManager)
    }

    @Provides
    @Singleton
    fun provideControlRepository(webSocketManager: WebSocketManager): ControlRepository {
        return ControlRepository(webSocketManager)
    }

    @Provides
    @Singleton
    fun provideResources(@ApplicationContext context: Context): Resources {
        return context.resources
    }
}