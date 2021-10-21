package com.rex50.composecars

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.rex50.composecars.ui.theme.ComposeRandomCarTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@ExperimentalCoilApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeRandomCarTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    //Greeting("Android")
                    CarSection()
                }
            }
        }
    }
}

@ExperimentalCoilApi
@Composable
fun CarSection() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
    ) {

        val viewModel: MainViewModel = hiltViewModel()
        val car = viewModel.state.value.car
        val isLoading = viewModel.state.value.isLoading

        car?.let {
            Image(painter = rememberImagePainter(
                data = car.imageUrl,
                builder = { crossfade(true)}
            ), contentDescription = "Car")

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = car.name,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Type: ${car.type}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Doors: ${car.doors}")
        }
        Button(
            onClick = viewModel::getRandomCar,
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = "New Car!")
        }
        Spacer(modifier = Modifier.height(8.dp))
        if(isLoading) {
            CircularProgressIndicator()
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeRandomCarTheme {
        Greeting("Android")
    }
}