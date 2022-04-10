package com.plcoding.cryptocurrencyappyt.presentation.coinList

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.cryptocurrencyappyt.common.Resource
import com.plcoding.cryptocurrencyappyt.domain.entity.UserMessage
import com.plcoding.cryptocurrencyappyt.domain.useCase.getCoins.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase,
) : ViewModel() {

    private val _state = mutableStateOf(CoinListUiState())
    val state: State<CoinListUiState> = _state

    init {
        getCoins()
    }

    private var getCoinJob: Job? = null

    private fun getCoins(refresh: Boolean = false) {
        getCoinJob?.cancel()
        getCoinJob = viewModelScope.launch {
            getCoinsUseCase(refresh).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = _state.value.copy(
                            isLoading = false,
                            coinList = result.data ?: emptyList(),
                        )
                    }
                    is Resource.Error -> {
                        makeUserMessage(result.message ?: "An error occurred.")
                    }
                    is Resource.Loading -> {
                        _state.value = _state.value.copy(isLoading = true)
                    }
                }
            }
        }
    }

    /**
     * Make a new user message with a unique id and add it to the list of user messages.
     *
     * @param messageText The text of the message to be sent.
     * @return Nothing.
     */
    private fun makeUserMessage(messageText: String) {

        if (messageText.isBlank()) return
        viewModelScope.launch {
            val messages = state.value.errorMessage + UserMessage(
                id = UUID.randomUUID().mostSignificantBits,
                message = messageText
            )
            _state.value = _state.value.copy(errorMessage = messages)
        }
    }

    /**
     * Remove the message after it is shown with the given id from the user messages
     *
     * @param messageId The id of the message that was shown.
     */
    fun userMessageShown(messageId: Long) {
        viewModelScope.launch {
            val messages = state.value.errorMessage.filterNot { it.id == messageId }
            _state.value = _state.value.copy(errorMessage = messages)
        }
    }

}