package com.gmail.orlandroyd.beerace.feature_race.presentation.screen_win

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.gmail.orlandroyd.beerace.core.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WinScreenViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(WinState())
    val state: State<WinState> = _state

    init {
        getBeeArguments()
    }

    private fun getBeeArguments() {

        val name = savedStateHandle.get<String>(
            key = Screen.BEE_NAME_ARGUMENT_KEY
        ).orEmpty()

        val color = savedStateHandle.get<String>(
            key = Screen.BEE_COLOR_ARGUMENT_KEY
        ).orEmpty()

        _state.value = state.value.copy(
            name = name,
            color = color
        )
    }
}