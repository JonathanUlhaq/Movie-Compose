package com.belajar.movielistapp.home

import android.widget.Space
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.belajar.movielistapp.datasource.getMovies
import com.belajar.movielistapp.model.MovieModel
import com.belajar.movielistapp.route.Routes
import com.belajar.movielistapp.widget.MovieCard
import com.belajar.movielistapp.widget.TopBar

@Composable
fun HomeLayout(
    navController: NavController
) {
    Scaffold(
        topBar = {
            TopBar(navController = navController,
                backIconVisible = false )
        }
    ) {
        Column(Modifier.padding(it)) {
            HomeBody(navController)
        }
    }
}


@Composable
fun HomeBody(
    navController: NavController
) {
    val visibility = remember {
        mutableStateOf(false)
    }

    var currentIndex:Int? by remember {
        mutableStateOf(null)
    }

    val listMovie = getMovies()

    LazyColumn(content = {
        itemsIndexed(listMovie) { index,movie ->
            visibility.value =  index == currentIndex
            MovieCard(movie = movie,
                visibility = visibility.value,
                onDetail = {
                    navController.navigate(Routes.DetailScreen.route+"/${movie.id}")
                } ) {

                if(currentIndex == null) {
                    currentIndex = index
                } else {
                    currentIndex = null
                }


            }
        }
    })
}
