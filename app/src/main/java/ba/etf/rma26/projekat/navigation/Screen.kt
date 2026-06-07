package ba.etf.rma26.projekat.navigation

sealed class Screen(val route: String) {
    object FilterScreen : Screen("filter_screen")
    object KvizoviScreen : Screen("kvizovi_screen")
    object PitanjaScreen : Screen("pitanja_screen/{idKviza}") {
        fun createRoute(idKviza: Int): String = "pitanja_screen/$idKviza"
    }
}