package com.plcoding.cryptocurrencyappyt.domain.entity

data class Coin(
    val id: String,
    val isActive: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
)
