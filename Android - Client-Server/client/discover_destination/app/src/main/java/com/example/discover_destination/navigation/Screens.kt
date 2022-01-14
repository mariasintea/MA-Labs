package com.example.discover_destination.navigation

sealed class Screens(val route: String) {
    object LogIn : Screens(route = "login")
    object Start : Screens(route = "start")
    object Add : Screens(route = "add")
    object Modify : Screens(route = "modify")
    object Edit : Screens(route = "edit")
    object Delete : Screens(route = "delete")
    object ViewAll : Screens(route = "viewAll")
    object ViewOne : Screens(route = "viewOne")
}