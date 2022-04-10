package com.plcoding.cryptocurrencyappyt.di

import com.plcoding.cryptocurrencyappyt.common.Constants
import com.plcoding.cryptocurrencyappyt.data.dataSource.CoinRemoteDataSource
import com.plcoding.cryptocurrencyappyt.data.dataSource.api.CoinPaprikaApiService
import com.plcoding.cryptocurrencyappyt.data.repository.DefaultCoinRepository
import com.plcoding.cryptocurrencyappyt.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideCoinPaprikaApiService(): CoinPaprikaApiService {
        return Retrofit.Builder().baseUrl(Constants.COIN_PAPRIKA_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinPaprikaApiService::class.java)
    }


    @Provides
    @Singleton
    fun provideCoinRemoteDataSource(apiService: CoinPaprikaApiService): CoinRemoteDataSource {
        return CoinRemoteDataSource(apiService)
    }

    @Provides
    fun provideExternalScope(): CoroutineScope {
        return CoroutineScope(SupervisorJob() + Dispatchers.Default)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(
        coinRemoteDataSource: CoinRemoteDataSource,
        externalScope: CoroutineScope,
    ): CoinRepository {
        return DefaultCoinRepository(coinRemoteDataSource, externalScope)
    }
}