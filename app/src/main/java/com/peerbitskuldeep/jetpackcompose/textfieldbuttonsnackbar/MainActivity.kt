package com.peerbitskuldeep.jetpackcompose.textfieldbuttonsnackbar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.peerbitskuldeep.jetpackcompose.textfieldbuttonsnackbar.ui.theme.TextFieldButtonSnackBarTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val scaffoldState = rememberScaffoldState()

//            var textFieldState = remember is same
            var textFieldState by remember{
                mutableStateOf("")
            }

            val scope = rememberCoroutineScope()

            //interact with LayOut
            Scaffold(
                Modifier.fillMaxSize(),
                scaffoldState = scaffoldState
            ) {

                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(horizontal = 30.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                )
                {

                    TextField(
                        value = textFieldState,
                        label = {
                            Text(text = "Enter your name: ")
                        },
                        onValueChange = {
                            textFieldState = it
                        },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Box(modifier = Modifier.requiredHeight(IntrinsicSize.Max)
                        .requiredWidth(IntrinsicSize.Max), contentAlignment = Alignment.CenterEnd) {

                        Button(onClick = {

                            scope.launch {
                                scaffoldState.snackbarHostState.showSnackbar("Hey $textFieldState")
                            }

                        }) {
                            Text(text = "Hit It")
                        }

                    }

                }
            }
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
    TextFieldButtonSnackBarTheme {
        Greeting("Android")
    }
}