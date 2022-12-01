package com.belajar.movielistapp.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.belajar.movielistapp.detail.DetailScreen
import com.belajar.movielistapp.home.HomeLayout
import com.belajar.movielistapp.route.Routes
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavigationAdapter() {
    val animatedNavController = rememberAnimatedNavController()

    AnimatedNavHost(navController = animatedNavController,
        startDestination = Routes.HomeScreen.route , builder ={
            composable(Routes.HomeScreen.route) {
                HomeLayout(navController = animatedNavController)
            }

            composable(Routes.DetailScreen.route + "/{id}",
                        arguments = listOf(navArgument("id") {
                            type = NavType.StringType
                        })
            ) { argument ->
                argument.arguments?.getString("id")
                    ?.let { DetailScreen(id = it, navController = animatedNavController) }
            }
        } )
}