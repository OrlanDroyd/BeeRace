package com.gmail.orlandroyd.beerace.feature_race.presentation.screen_home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.orlandroyd.beerace.feature_race.domain.usecase.GetRaceTimeUseCase
import com.gmail.orlandroyd.beerace.feature_race.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RaceHomeViewModel @Inject constructor(
    private val getRaceTimeUseCase: GetRaceTimeUseCase
) : ViewModel() {

    // TODO: Pass an executor-coroutine provider by parameter

    private val _state = mutableStateOf(RaceHomeState())
    val state: State<RaceHomeState> = _state

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onStartAction() {
        viewModelScope.launch {
            getRaceTimeUseCase()
                .onEach { result ->
                    when (result) {
                        is Resource.Success -> {
                            _state.value = state.value.copy(
                                time = result.data?.timeInSeconds,
                                isLoading = false,
                                errorMessage = ""
                            )
                            _eventFlow.emit(
                                UIEvent.NavigateToRace
                                    (timeLeft = result.data?.timeInSeconds)
                            )
                        }

                        is Resource.Error -> {
                            _state.value = state.value.copy(
                                isLoading = false,
                                errorMessage = result.message ?: "Unknown error"
                            )
                        }

                        is Resource.Loading -> {
                            _state.value = state.value.copy(
                                isLoading = true,
                                errorMessage = ""
                            )
                        }
                    }
                }.launchIn(this)
        }
    }

    sealed class UIEvent {
        data class NavigateToRace(val timeLeft: String?) : UIEvent()
    }

}