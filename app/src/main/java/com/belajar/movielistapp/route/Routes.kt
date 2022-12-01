package com.belajar.movielistapp.route

class Routes {
    sealed class Route(val route:String)
    object HomeScreen:Route("home")
    object DetailScreen:Route("detail")
}