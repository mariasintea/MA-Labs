package com.example.discover_destination.navigation

sealed class Screen(val route: String) {
    object LogIn : Screen(route = "login")
    object Start : Screen(route = "start")
    object Add : Screen(route = "add")
    object Modify : Screen(route = "modify")
    object Edit : Screen(route = "edit")
    object Delete : Screen(route = "delete")
    object ViewAll : Screen(route = "viewAll")
    object ViewOne : Screen(route = "viewOne")
}