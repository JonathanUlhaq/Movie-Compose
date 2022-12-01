package com.belajar.movielistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.belajar.movielistapp.home.HomeLayout
import com.belajar.movielistapp.navigation.NavigationAdapter
import com.belajar.movielistapp.ui.theme.MovieListAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                MovieApp {
                    NavigationAdapter()
                }
        }
    }
}

@Composable
fun MovieApp(contentApp:@Composable() ()-> Unit) {
    MovieListAppTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            contentApp.invoke()
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MovieListAppTheme {

    }
}