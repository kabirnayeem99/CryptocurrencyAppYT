package com.plcoding.cryptocurrencyappyt.presentation.coinList

import com.plcoding.cryptocurrencyappyt.domain.entity.Coin
import com.plcoding.cryptocurrencyappyt.domain.entity.UserMessage

data class CoinListUiState(
    val isLoading: Boolean = false,
    val errorMessage: List<UserMessage> = emptyList(),
    val coinList: List<Coin> = emptyList(),
)