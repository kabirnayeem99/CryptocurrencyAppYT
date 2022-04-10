package com.plcoding.cryptocurrencyappyt.data.dto.coin


import com.google.gson.annotations.SerializedName
import com.plcoding.cryptocurrencyappyt.domain.entity.Coin

data class CoinDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("is_active")
    val isActive: Boolean,
    @SerializedName("name")
    val name: String,
    @SerializedName("rank")
    val rank: Int,
    @SerializedName("symbol")
    val symbol: String,

    @SerializedName("is_new")
    val isNew: Boolean,
    @SerializedName("type")
    val type: String
)

fun CoinDto.toCoin(): Coin {
    return Coin(id, isActive, name, rank, symbol)
}