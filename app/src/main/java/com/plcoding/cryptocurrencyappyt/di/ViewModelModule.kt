package com.plcoding.cryptocurrencyappyt.di

import com.plcoding.cryptocurrencyappyt.domain.repository.CoinRepository
import com.plcoding.cryptocurrencyappyt.domain.useCase.getCoin.GetCoinUseCase
import com.plcoding.cryptocurrencyappyt.domain.useCase.getCoins.GetCoinsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    fun provideGetCoinUseCase(coinRepository: CoinRepository): GetCoinUseCase {
        return GetCoinUseCase(coinRepository)
    }

    @Provides
    fun provideGetCoinsUseCase(coinRepository: CoinRepository): GetCoinsUseCase {
        return GetCoinsUseCase(coinRepository)
    }
}