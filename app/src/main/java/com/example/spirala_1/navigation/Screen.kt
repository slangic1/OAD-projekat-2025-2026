package com.example.spirala_1.navigation

sealed class Screen(val route: String) {
    object FilterScreen : Screen("filter_screen")
    object KvizoviScreen : Screen("kvizovi_screen")
}