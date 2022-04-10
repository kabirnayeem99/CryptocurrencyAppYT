package com.plcoding.cryptocurrencyappyt.domain.repository

import com.plcoding.cryptocurrencyappyt.data.dto.coin.CoinDto
import com.plcoding.cryptocurrencyappyt.data.dto.coinDetails.CoinDetailDto

interface CoinRepository {
    suspend fun getCoins(refresh: Boolean = false): List<CoinDto>

    suspend fun getCoinById(coinId: String): CoinDetailDto
}