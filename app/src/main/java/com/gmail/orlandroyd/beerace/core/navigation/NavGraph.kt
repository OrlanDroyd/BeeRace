package com.gmail.orlandroyd.beerace.core.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.gmail.orlandroyd.beerace.feature_race.domain.model.BeeDomainModel
import com.gmail.orlandroyd.beerace.feature_race.presentation.screen_authentication.CaptchaScreen
import com.gmail.orlandroyd.beerace.feature_race.presentation.screen_error.ErrorScreen
import com.gmail.orlandroyd.beerace.feature_race.presentation.screen_home.RaceHomeScreen
import com.gmail.orlandroyd.beerace.feature_race.presentation.screen_home.RaceHomeViewModel
import com.gmail.orlandroyd.beerace.feature_race.presentation.screen_race.RaceScreen
import com.gmail.orlandroyd.beerace.feature_race.presentation.screen_race.RaceViewModel
import com.gmail.orlandroyd.beerace.feature_race.presentation.screen_win.WinScreen
import com.gmail.orlandroyd.beerace.feature_race.presentation.screen_win.WinScreenViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SetupNavGraph(
    startDestination: String,
    navController: NavHostController
) {
    NavHost(
        startDestination = startDestination,
        navController = navController
    ) {

        raceSetupRoute(
            navigateToRaceBees = {
                navController.navigate(Screen.RaceBees.passTime(time = it))
            }
        )

        raceBeesRoute(
            navigateToAuthentication = {
                navController.navigate(Screen.Authentication.route)
            },
            navigateToError = {
                navController.navigate(Screen.Error.route)
            },
            navigateToWin = {
                navController.popBackStack()
                navController.navigate(
                    Screen.BeeWin.passBeeInfo(
                        beeName = it.name,
                        beeColor = it.color
                    )
                )
            }
        )

        authenticationRoute(
            onClose = {
                navController.navigateUp()
            }
        )

        errorRoute()

        winRoute(
            onRestart = {
                navController.popBackStack()
            }
        )

    }
}

private fun NavGraphBuilder.raceSetupRoute(
    navigateToRaceBees: (String) -> Unit
) {
    composable(route = Screen.Home.route) {
        val viewModel: RaceHomeViewModel = hiltViewModel()
        val state = viewModel.state.value

        RaceHomeScreen(
            onStartAction = {
                viewModel.onStartAction()
            },
            state = state
        )

        LaunchedEffect(Unit) {
            viewModel.eventFlow.collectLatest { event ->
                when (event) {
                    is RaceHomeViewModel.UIEvent.NavigateToRace -> {
                        Log.d("DEBUG-MSG", "raceSetupRoute: timeLeft -> ${event.timeLeft}")
                        event.timeLeft?.let { timeLeft -> navigateToRaceBees(timeLeft) }
                    }
                }
            }
        }

    }
}

private fun NavGraphBuilder.raceBeesRoute(
    navigateToAuthentication: () -> Unit,
    navigateToError: () -> Unit,
    navigateToWin: (bee: BeeDomainModel) -> Unit,
) {
    composable(route = Screen.RaceBees.route) {

        val viewModel: RaceViewModel = hiltViewModel()
        val state = viewModel.state.value
        RaceScreen(state = state)

        LaunchedEffect(Unit) {
            viewModel.eventFlow.collectLatest { event ->
                when (event) {
                    RaceViewModel.UIEvent.NavigateToAuthentication -> {
                        navigateToAuthentication()
                    }

                    is RaceViewModel.UIEvent.NavigateToError -> {
                        navigateToError()
                    }

                    is RaceViewModel.UIEvent.NavigateToWin -> {
                        Log.d("DEBUG-MSG", "NavigateToWin: bee -> ${event.bee}")
                        navigateToWin(event.bee)
                    }
                }
            }
        }
    }
}

private fun NavGraphBuilder.authenticationRoute(
    onClose: () -> Unit
) {
    composable(route = Screen.Authentication.route) {
        CaptchaScreen(
            onClose = onClose,
            isSuccess = {
                if (it) {
                    onClose()
                }
            })
    }
}

private fun NavGraphBuilder.errorRoute() {
    composable(route = Screen.Error.route) {
        ErrorScreen()
    }
}

private fun NavGraphBuilder.winRoute(
    onRestart: () -> Unit
) {
    composable(route = Screen.BeeWin.route) {
        val viewModel: WinScreenViewModel = hiltViewModel()
        val state = viewModel.state.value

        WinScreen(
            beeName = state.name,
            beeColor = state.color.replace("#", ""), // remove `#` and pass by navArgs
            onRestart = onRestart
        )
    }
}