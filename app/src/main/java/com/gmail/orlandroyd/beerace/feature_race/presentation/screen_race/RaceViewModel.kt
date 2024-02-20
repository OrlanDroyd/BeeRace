package com.gmail.orlandroyd.beerace.feature_race.presentation.screen_race

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.orlandroyd.beerace.core.navigation.Screen
import com.gmail.orlandroyd.beerace.feature_race.domain.model.BeeDomainModel
import com.gmail.orlandroyd.beerace.feature_race.domain.usecase.GetBeesUseCase
import com.gmail.orlandroyd.beerace.feature_race.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicInteger
import javax.inject.Inject
import kotlin.properties.Delegates

@HiltViewModel
class RaceViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getBeesUseCase: GetBeesUseCase
) : ViewModel() {

    companion object {
        const val TAG = "RaceViewModel"
    }

    private val _state = mutableStateOf(RaceState())
    val state: State<RaceState> = _state

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    // TODO: Pass an executor-coroutine provider by parameter
    private var job: Job? = null

    private var countdownJob: Job? = null
    private var remainingTime by Delegates.notNull<Int>()
    private var initialTime by Delegates.notNull<Int>()

    private val viewModelScopeDefault = CoroutineScope(Dispatchers.Default)
    private val requestsCounter = AtomicInteger(0)

    init {
        getTimeIdArgument()
        startCountdown()
        schedulePeriodicRequests()
    }

    private fun schedulePeriodicRequests() {
        job = viewModelScope.launch {
            while (true) {
                delay(3000) // wait at lest 2s

                if (requestsCounter.incrementAndGet() <= 30 && remainingTime != 0) {
                    getBeesUseCase()
                        .onEach { result ->
                            when (result) {
                                is Resource.Success -> {
                                    Log.i(TAG, "success: ${result.data}")
                                    _state.value = state.value.copy(
                                        bees = result.data ?: emptyList(),
                                        isLoading = false
                                    )
                                }

                                is Resource.Error -> {
                                    Log.i(TAG, "error: msg: ${result.message} code: ${result.code}")
                                    _state.value = state.value.copy(
                                        isLoading = false
                                    )
                                    if (result.code == 403) {
                                        _eventFlow.emit(UIEvent.NavigateToAuthentication)
                                    } else {
                                        _eventFlow.emit(UIEvent.NavigateToError)
                                    }
                                }

                                is Resource.Loading -> {
                                    Log.i(TAG, "loading: ${result.data}")
                                    _state.value = state.value.copy(
                                        isLoading = true
                                    )
                                }
                            }
                        }.launchIn(this)
                } else {
                    if (remainingTime == 0) {
                        _eventFlow.emit(UIEvent.NavigateToWin(state.value.bees.first()))
                    }
                }

                // Restart counter
                if (requestsCounter.get() > 30) {
                    requestsCounter.set(0)
                }
            }
        }
    }

    private fun getTimeIdArgument() {
        remainingTime = savedStateHandle.get<String>(
            key = Screen.TIME_ARGUMENT_KEY
        )?.toInt() ?: 0
        initialTime = remainingTime
    }

    private fun startCountdown() {
        countdownJob?.cancel()
        countdownJob = viewModelScope.launch {
            while (remainingTime > 0) {
                remainingTime--
                _state.value = state.value.copy(
                    time = remainingTime.toFormattedTime(),
                    progress = getProgress(remainingTime)
                )
                delay(1000)
            }
        }
    }

    private fun getProgress(remainingTime: Int) =
        ((initialTime - remainingTime) * 100f / initialTime) / 100


    private fun stopCountdown() {
        countdownJob?.cancel()
    }

    override fun onCleared() {
        super.onCleared()
        stopCountdown()
        viewModelScopeDefault.cancel()
        job?.cancel()
    }

    sealed class UIEvent {
        data class NavigateToWin(val bee: BeeDomainModel) : UIEvent()
        data object NavigateToAuthentication : UIEvent()
        data object NavigateToError : UIEvent()
    }

}

private fun Int.toFormattedTime(): String {
    val minutes = this / 60
    val seconds = this % 60
    return "%02d:%02d".format(minutes, seconds)
}