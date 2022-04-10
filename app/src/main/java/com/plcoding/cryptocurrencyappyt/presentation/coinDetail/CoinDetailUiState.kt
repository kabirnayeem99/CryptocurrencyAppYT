package com.plcoding.cryptocurrencyappyt.presentation.coinDetail

import com.plcoding.cryptocurrencyappyt.domain.entity.CoinDetail
import com.plcoding.cryptocurrencyappyt.domain.entity.UserMessage

data class CoinDetailUiState(
    val isLoading: Boolean = false,
    val errorMessage: List<UserMessage> = emptyList(),
    val coinDetail: CoinDetail? = null,
)