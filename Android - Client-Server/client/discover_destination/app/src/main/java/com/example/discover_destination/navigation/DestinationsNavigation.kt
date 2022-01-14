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

    NavHost(navController = navController, startDestination = Screens.LogIn.route) {
        composable(
            route = "${Screens.Start.route}/{user}",
            arguments = listOf(navArgument("user") { type = NavType.StringType })
        ) { navBackStackEntry ->
            navBackStackEntry.arguments?.get("user")?.let { user ->
                StartScreen(
                    onAddClick = {
                        navController.navigate("${Screens.Add.route}/$user")
                    },
                    onDeleteClick = {
                        navController.navigate("${Screens.Delete.route}/$user")
                    },
                    onModifyClick = {
                        navController.navigate("${Screens.Modify.route}/$user")
                    },
                    onAllClick = {
                        navController.navigate("${Screens.ViewAll.route}/$user")
                    }
                )
            }
        }

        composable(
            route = Screens.LogIn.route
        ) {
            LogInScreen(
                onFinishedOperation = { user ->
                    navController.navigate(route = "${Screens.Start.route}/$user")
                }
            )
        }

        composable(
            route = "${Screens.Add.route}/{user}",
            arguments = listOf(navArgument("user") { type = NavType.StringType })
        ) { navBackStackEntry ->
            navBackStackEntry.arguments?.get("user")?.let { user ->
                AddScreen(
                    onFinishedOperation = {
                        navController.navigate(route = "${Screens.Start.route}/$user")
                    },
                    user = user as String
                )
            }
        }

        composable(
            route = "${Screens.Delete.route}/{user}",
            arguments = listOf(navArgument("user") { type = NavType.StringType })
        ) { navBackStackEntry ->
            navBackStackEntry.arguments?.get("user")?.let { user ->
                DeleteScreen(
                    onFinishedOperation = {
                        navController.navigate(route = "${Screens.Start.route}/$user")
                    },
                    user = user as String
                )
            }
        }

        composable(
            route = "${Screens.Modify.route}/{user}",
            arguments = listOf(navArgument("user") { type = NavType.StringType })
        ) { navBackStackEntry ->
            navBackStackEntry.arguments?.get("user")?.let { user ->
                ModifyScreen(
                    onDestinationClick = { city ->
                        navController.navigate("${Screens.Edit.route}/$user/$city")
                    },
                    user = user as String
                )
            }
        }

        composable(
            route = "${Screens.Edit.route}/{user}/{city}",
            arguments = listOf(navArgument("user") { type = NavType.StringType },
                navArgument("city") { type = NavType.StringType })
        ) { navBackStackEntry ->
            navBackStackEntry.arguments?.let { args ->
                EditScreen(
                    city = args.get("city") as String,
                    user = args.get("user") as String,
                    onFinishedOperation = {
                        navController.navigate(route = "${Screens.Start.route}/${args.get("user")}")
                    }
                )
            }
        }

        composable(
            route = "${Screens.ViewAll.route}/{user}",
            arguments = listOf(navArgument("user") { type = NavType.StringType })
        ) { navBackStackEntry ->
            navBackStackEntry.arguments?.get("user")?.let { user ->
                ViewAllScreen(
                    onDestinationClick = { city ->
                        navController.navigate("${Screens.ViewOne.route}/$city")
                    },
                    user = user as String
                )
            }
        }

        composable(
            route = "${Screens.ViewOne.route}/{city}",
            arguments = listOf(navArgument("city") { type = NavType.StringType })
        ) { navBackStackEntry ->
            navBackStackEntry.arguments?.get("city")?.let { city ->
                ViewOneScreen(city = city as String)
            }
        }

    }
}
