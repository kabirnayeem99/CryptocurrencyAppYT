package com.plcoding.cryptocurrencyappyt.domain.useCase.getCoins

import android.util.Log
import com.plcoding.cryptocurrencyappyt.common.Resource
import com.plcoding.cryptocurrencyappyt.data.dto.coin.toCoin
import com.plcoding.cryptocurrencyappyt.domain.entity.Coin
import com.plcoding.cryptocurrencyappyt.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

private const val TAG = "GetCoinsUseCase"
class GetCoinsUseCase @Inject constructor(
    private val coinRepository: CoinRepository,
) {
    operator fun invoke(refresh: Boolean): Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading<List<Coin>>())
            val coins = coinRepository.getCoins(refresh).map { it.toCoin() }
            Log.d(TAG, "invoke: $coins")
            emit(Resource.Success(coins))
        } catch (e: HttpException) {
            emit(Resource.Error<List<Coin>>(e.localizedMessage ?: "Could not get the data from the server"))
        } catch (e: IOException) {
            emit(Resource.Error<List<Coin>>("Check your internet connection."))
        } catch (e: Exception) {
            emit(Resource.Error<List<Coin>>(e.localizedMessage))
        }
    }
}