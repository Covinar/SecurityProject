package com.example.domain.di

import com.example.domain.gateways.AuthGateway
import com.example.domain.usecases.SignInUseCase
import com.example.domain.usecases.SignInUseCaseImpl
import com.example.domain.usecases.SignUpUseCase
import com.example.domain.usecases.SignUpUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    fun provideSignInUseCase(authGateway: AuthGateway): SignInUseCase = SignInUseCaseImpl(authGateway)

    @Provides
    fun provideSignUpUseCase(authGateway: AuthGateway): SignUpUseCase = SignUpUseCaseImpl(authGateway)

}