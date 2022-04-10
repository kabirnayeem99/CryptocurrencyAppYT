package com.plcoding.cryptocurrencyappyt.data.dataSource.api

import com.plcoding.cryptocurrencyappyt.data.dto.coin.CoinDto
import com.plcoding.cryptocurrencyappyt.data.dto.coinDetails.CoinDetailDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinPaprikaApiService {
    @GET("v1/coins")
    suspend fun getAllCoins(): List<CoinDto>

    @GET("v1/coins/{id}")
    suspend fun getCoinDetails(@Path("id") coinId: String): CoinDetailDto
}