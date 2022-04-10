package com.plcoding.cryptocurrencyappyt.domain.useCase.getCoin

import com.plcoding.cryptocurrencyappyt.common.Resource
import com.plcoding.cryptocurrencyappyt.data.dto.coinDetails.toCoinDetail
import com.plcoding.cryptocurrencyappyt.domain.entity.CoinDetail
import com.plcoding.cryptocurrencyappyt.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val coinRepository: CoinRepository,
) {
    operator fun invoke(coinId: String): Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading<CoinDetail>())
            val coinDetail = coinRepository.getCoinById(coinId).toCoinDetail()
            emit(Resource.Success(coinDetail))
        } catch (e: HttpException) {
            emit(Resource.Error<CoinDetail>(e.localizedMessage ?: "Could not get the data from the server"))
        } catch (e: IOException) {
            emit(Resource.Error<CoinDetail>("Check your internet connection."))
        } catch (e: Exception) {
            emit(Resource.Error<CoinDetail>(e.localizedMessage))
        }
    }
}