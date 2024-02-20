package com.gmail.orlandroyd.beerace.core.navigation

sealed class Screen(val route: String) {

    companion object {
        const val HOME = "home"

    }

    data object Home : Screen(route = HOME)

}
