package com.gmail.orlandroyd.beerace.core.navigation

sealed class Screen(val route: String) {

    companion object {
        const val HOME = "home"
        const val RACE_BEES = "race_screen"
        const val AUTHENTICATION = "authentication"
        const val ERROR = "error"

        const val TIME_ARGUMENT_KEY = "time_left"
    }

    data object Home : Screen(route = HOME)

    data object RaceBees : Screen(
        route = "$RACE_BEES?$TIME_ARGUMENT_KEY=" +
                "{$TIME_ARGUMENT_KEY}"
    ) {
        fun passTime(time: String) =
            "$RACE_BEES?$TIME_ARGUMENT_KEY=$time"
    }

    data object Authentication : Screen(route = AUTHENTICATION)

    data object Error : Screen(route = ERROR)

}
