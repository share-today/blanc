package com.wswon.blanc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.wswon.blanc.ui.theme.BlancTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BlancTheme {
                // A surface container using the 'background' color from the theme
                MainScreen()
            }
        }
    }

    @Composable
    private fun MainScreen(
        viewModel: HomeViewModel = hiltViewModel()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
    //                    color = MaterialTheme.colorScheme.background
        ) {
            ShareTodayPost(Modifier)
        }
    }
}


@Composable
fun ShareTodayPost(modifier: Modifier) {

    Box(
        modifier
            .width(325.dp)
            .height(325.dp)
            .background(Color(0xFFA6A6A6))
    ) {
        InputText(Modifier) {

        }
    }
}


@Composable
fun InputText(modifier: Modifier, onTextChanged: () -> Unit) {
    var value by rememberSaveable { mutableStateOf("Today Share Today") }

    BasicTextField(
        value = value,
        onValueChange = {
            value = it
            onTextChanged()
        },
        maxLines = 2,
        textStyle = TextStyle(
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Medium
        ),
        modifier = modifier.padding(20.dp)
    )
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BlancTheme {
        Greeting("Android")
        ShareTodayPost(Modifier)
    }
}