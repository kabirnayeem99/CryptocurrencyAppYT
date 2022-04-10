package com.plcoding.cryptocurrencyappyt.data.repository

import android.util.Log
import com.plcoding.cryptocurrencyappyt.data.dataSource.CoinRemoteDataSource
import com.plcoding.cryptocurrencyappyt.data.dto.coin.CoinDto
import com.plcoding.cryptocurrencyappyt.data.dto.coinDetails.CoinDetailDto
import com.plcoding.cryptocurrencyappyt.domain.repository.CoinRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import javax.inject.Inject

private const val TAG = "DefaultCoinRepository"

class DefaultCoinRepository @Inject constructor(
    private val coinRemoteDataSource: CoinRemoteDataSource,
    private val externalScope: CoroutineScope, // SupervisorJob() + Dispatchers.Default
) : CoinRepository {

    private val latestCoinListMutex = Mutex()
    private var latestCoinList: List<CoinDto> = emptyList()

    override suspend fun getCoins(refresh: Boolean): List<CoinDto> {
        return withContext(externalScope.coroutineContext) {
            val isCachedListEmpty = latestCoinListMutex.withLock { latestCoinList.isEmpty() }
            if (refresh || isCachedListEmpty) {
                val coinDtoList = coinRemoteDataSource.getCoinList()
                Log.d(TAG, "getCoins: $coinDtoList")
                if (coinDtoList.isNotEmpty()) {
                    latestCoinListMutex.withLock {
                        latestCoinList = coinDtoList
                    }
                    coinDtoList
                } else emptyList()
            } else {
                latestCoinListMutex.withLock { latestCoinList }
            }
        }
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDto {
        return withContext(externalScope.coroutineContext) {
            val coinDetail = coinRemoteDataSource.getCoinById(coinId = coinId)
            coinDetail
        }

    }
}