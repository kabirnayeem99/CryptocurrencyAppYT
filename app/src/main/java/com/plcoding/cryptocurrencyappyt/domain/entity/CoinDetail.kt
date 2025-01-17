package com.plcoding.cryptocurrencyappyt.domain.entity

import com.plcoding.cryptocurrencyappyt.data.dto.coinDetails.CoinDetailDto

data class CoinDetail(
    val coinId: String,
    val name: String,
    val description: String,
    val symbol: String,
    val rank: Int,
    val isActive: Boolean,
    val tags: List<String>,
    val team: List<CoinDetailDto.TeamMember>,
)
