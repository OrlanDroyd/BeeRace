package com.gmail.orlandroyd.beerace.core.navigation

sealed class Screen(val route: String) {

    companion object {
        const val HOME = "home"
        const val RACE_BEES = "race_screen"
        const val AUTHENTICATION = "authentication"
        const val ERROR = "error"
        const val BEE_WIN = "bee_win"

        const val TIME_ARGUMENT_KEY = "time_left"
        const val BEE_NAME_ARGUMENT_KEY = "bee_name"
        const val BEE_COLOR_ARGUMENT_KEY = "bee_color"
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

    data object BeeWin : Screen(
        route = "$BEE_WIN/$BEE_NAME_ARGUMENT_KEY=" +
                "{$BEE_NAME_ARGUMENT_KEY}/$BEE_COLOR_ARGUMENT_KEY=" +
                "{$BEE_COLOR_ARGUMENT_KEY}"
    ) {
        fun passBeeInfo(beeName: String, beeColor: String) =
            "$BEE_WIN/$BEE_NAME_ARGUMENT_KEY=$beeName/$BEE_COLOR_ARGUMENT_KEY=$beeColor"
    }

}
