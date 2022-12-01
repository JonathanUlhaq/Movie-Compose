package com.belajar.movielistapp.widget

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.belajar.movielistapp.model.MovieModel


@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    navController: NavController,
    backIconVisible:Boolean
) {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.background
    ) {
        Surface(
            modifier = modifier
                .padding(start = 14.dp)
        ) {
           Row (
               verticalAlignment = Alignment.CenterVertically
                   ) {
               if (backIconVisible) IconButton(onClick = {navController.popBackStack() }) {
                   Icon(imageVector = Icons.Default.ArrowBack,
                       contentDescription = "Back" )
               }

               Text(text = "Movie Compose")
           }
        }
    }
}


@Composable
fun MovieCard(
    movie: MovieModel,
    visibility:Boolean,
    onDetail:() -> Unit = {  },
    onClick:()->Unit,
) {



    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onDetail.invoke() },
        backgroundColor = MaterialTheme.colors.background,
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                shape = CircleShape
            ) {
                AsyncImage(model = "${movie.images[0]}",
                    contentDescription = "Movie Poster",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(100.dp))
            }

            Spacer(modifier = Modifier.width(20.dp))
            Surface {
                Column {
                    Text(text = movie.title,
                        style = MaterialTheme.typography.h6)
                    Text(text = "Director: ${movie.director}",
                        style = MaterialTheme.typography.body1)
                    Text(text = "Released: ${movie.year}",
                        style = MaterialTheme.typography.body1)
                    AnimatedVisibility(visible = visibility) {
                        Column {
                            Text(buildAnnotatedString {
                                append("Plot: ")

                                withStyle(
                                    SpanStyle(
                                        color = Color.LightGray
                                    )
                                ) {
                                    append("${movie.plot}")
                                }
                            })

                            Divider()
                            Text(text = "Actor: ${movie.actors}",
                                style = MaterialTheme.typography.body1)
                            Text(text = "Rating: ${movie.rating}",
                                style = MaterialTheme.typography.body1)
                        }
                    }

                    IconButton(onClick = {
                        onClick.invoke()
                    }) {
                        Icon(imageVector = if (visibility) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                            contentDescription = null )
                    }


                }
            }

        }
    }
}