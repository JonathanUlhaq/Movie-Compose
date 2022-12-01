package com.belajar.movielistapp.detail

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.belajar.movielistapp.datasource.getMovies
import com.belajar.movielistapp.model.MovieModel
import com.belajar.movielistapp.widget.MovieCard
import com.belajar.movielistapp.widget.TopBar

@Composable
fun DetailScreen(
    id:String,
    navController: NavController
) {
    val filterMovie = getMovies().filter { movie->
        movie.id == id
    }

    Scaffold(
        topBar = {
            TopBar(navController = navController ,
                backIconVisible = true )
        }
    ) {
        Column(Modifier.padding(it)) {
            DetailBody( movie = filterMovie)
            Spacer(modifier = Modifier.height(8.dp))
            ImageScrollableRow(movie = filterMovie)
        }
    }
}

@Composable
fun ImageScrollableRow(
    movie: List<MovieModel>
) {
    LazyRow(content = {
        items(movie.first().images) {
            AsyncImage(model = it,
                contentDescription = "Image",
                modifier = Modifier
                    .size(300.dp)
                    .padding(16.dp))
        }
    })
}

@Composable
fun DetailBody(
    movie:List<MovieModel>
) {


    
    val visibility = remember {
        mutableStateOf(false)
    }
    
    MovieCard(movie = movie.first(),
        visibility = visibility.value ) {
        visibility.value = !visibility.value
    }
    
    
    
}