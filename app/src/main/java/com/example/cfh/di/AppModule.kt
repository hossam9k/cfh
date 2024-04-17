package com.example.cfh.di

import com.example.cfh.auth.login.data.LoginRepositoryImpl
import com.example.cfh.auth.login.domain.repository.LoginRepository
import com.example.cfh.auth.login.domain.usecase.ValidateLoginInputUseCase
import com.example.cfh.auth.register.data.RegisterRepositoryImpl
import com.example.cfh.auth.register.domain.repository.RegisterRepository
import com.example.cfh.auth.register.domain.use_case.ValidateRegisterInputUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideValidateLoginInputUseCase(): ValidateLoginInputUseCase {
        return ValidateLoginInputUseCase()
    }

    @Provides
    @Singleton
    fun provideValidateRegisterInputUseCase(): ValidateRegisterInputUseCase {
        return ValidateRegisterInputUseCase()
    }

    @Provides
    @Singleton
    fun provideLoginRepository(): LoginRepository {
        return LoginRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideRegisterRepository(): RegisterRepository {
        return RegisterRepositoryImpl()
    }

}