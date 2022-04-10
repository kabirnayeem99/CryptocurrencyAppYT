package com.plcoding.cryptocurrencyappyt.data.dataSource

import com.plcoding.cryptocurrencyappyt.data.dataSource.api.CoinPaprikaApiService
import com.plcoding.cryptocurrencyappyt.data.dto.coin.CoinDto
import com.plcoding.cryptocurrencyappyt.data.dto.coinDetails.CoinDetailDto
import javax.inject.Inject

class CoinRemoteDataSource @Inject constructor(
    private val coinPaprikaApiService: CoinPaprikaApiService,
) {

    suspend fun getCoinList(): List<CoinDto> {
        return coinPaprikaApiService.getAllCoins()
    }

    suspend fun getCoinById(coinId: String): CoinDetailDto {
        return coinPaprikaApiService.getCoinDetails(coinId = coinId)
    }

}