package com.example.discover_destination.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.discover_destination.screens.*

@Composable
fun DestinationsNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.LogIn.route) {
        composable(
            route = "${Screen.Start.route}/{user}",
            arguments = listOf(navArgument("user") { type = NavType.StringType })
        ) { navBackStackEntry ->
            navBackStackEntry.arguments?.get("user")?.let { user ->
                StartScreen(
                    onAddClick = {
                        navController.navigate("${Screen.Add.route}/$user")
                    },
                    onDeleteClick = {
                        navController.navigate("${Screen.Delete.route}/$user")
                    },
                    onModifyClick = {
                        navController.navigate("${Screen.Modify.route}/$user")
                    },
                    onAllClick = {
                        navController.navigate("${Screen.ViewAll.route}/$user")
                    }
                )
            }
        }

        composable(
            route = Screen.LogIn.route
        ) {
            LogInScreen(
                onFinishedOperation = { user ->
                    navController.navigate(route = "${Screen.Start.route}/$user")
                }
            )
        }

        composable(
            route = "${Screen.Add.route}/{user}",
            arguments = listOf(navArgument("user") { type = NavType.StringType })
        ) { navBackStackEntry ->
            navBackStackEntry.arguments?.get("user")?.let { user ->
                AddScreen(
                    onFinishedOperation = {
                        navController.navigate(route = "${Screen.Start.route}/$user")
                    },
                    user = user as String
                )
            }
        }

        composable(
            route = "${Screen.Delete.route}/{user}",
            arguments = listOf(navArgument("user") { type = NavType.StringType })
        ) { navBackStackEntry ->
            navBackStackEntry.arguments?.get("user")?.let { user ->
                DeleteScreen(
                    onFinishedOperation = {
                        navController.navigate(route = "${Screen.Start.route}/$user")
                    },
                    user = user as String
                )
            }
        }

        composable(
            route = "${Screen.Modify.route}/{user}",
            arguments = listOf(navArgument("user") { type = NavType.StringType })
        ) { navBackStackEntry ->
            navBackStackEntry.arguments?.get("user")?.let { user ->
                ModifyScreen(
                    onDestinationClick = { city ->
                        navController.navigate("${Screen.Edit.route}/$user/$city")
                    },
                    user = user as String
                )
            }
        }

        composable(
            route = "${Screen.Edit.route}/{user}/{city}",
            arguments = listOf(navArgument("user") { type = NavType.StringType },
                navArgument("city") { type = NavType.StringType })
        ) { navBackStackEntry ->
            navBackStackEntry.arguments?.let { args ->
                EditScreen(
                    city = args.get("city") as String,
                    user = args.get("user") as String,
                    onFinishedOperation = {
                        navController.navigate(route = "${Screen.Start.route}/${args.get("user")}")
                    }
                )
            }
        }

        composable(
            route = "${Screen.ViewAll.route}/{user}",
            arguments = listOf(navArgument("user") { type = NavType.StringType })
        ) { navBackStackEntry ->
            navBackStackEntry.arguments?.get("user")?.let { user ->
                ViewAllScreen(
                    onDestinationClick = { city ->
                        navController.navigate("${Screen.ViewOne.route}/$city")
                    },
                    user = user as String
                )
            }
        }

        composable(
            route = "${Screen.ViewOne.route}/{city}",
            arguments = listOf(navArgument("city") { type = NavType.StringType })
        ) { navBackStackEntry ->
            navBackStackEntry.arguments?.get("city")?.let { city ->
                ViewOneScreen(city = city as String)
            }
        }

    }
}
