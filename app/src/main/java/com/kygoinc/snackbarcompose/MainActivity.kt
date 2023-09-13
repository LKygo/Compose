@file:OptIn(ExperimentalMaterial3Api::class)

package com.kygoinc.snackbarcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val snackbarHostState = remember { SnackbarHostState() }
            var textFieldState by remember {
                mutableStateOf("")
            }
            val scope = rememberCoroutineScope()
            var numberState by remember {
                mutableStateOf(0)
            }
            var numberString = numberState.toString()
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .background(Color.Yellow)

                ) {
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        snackbarHost = {
                            SnackbarHost(hostState = snackbarHostState)
                        })
                    { contentPadding ->
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Top,
                            modifier = Modifier
                                .padding(contentPadding)
                        ) {
                            TextField(value = textFieldState, label = {
                                Text(text = "Enter your name")
                            }, onValueChange = {
                                textFieldState = it
                            }, singleLine = true,
                                modifier = Modifier
                                    .padding(12.dp)
                                    .fillMaxWidth()

                            )
                            Spacer(modifier = Modifier.height(12.dp))

                            Button(onClick = {
                                scope.launch {
                                    snackbarHostState.showSnackbar("Hi there $textFieldState")
                                }
                            }) {
                                Text(text = "Say hi to me")
                            }
                        }

                    }
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize()
                        .background(Color.Red)
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        TextField(value = numberString, label = {
                            Text(text = "Number to add to")
                        }, onValueChange = {
                            numberString = it
                        }, modifier = Modifier
                            .padding(12.dp)
                            .fillMaxWidth()
                        )

                        Button(onClick = {
                            numberState++
                        }) {

                            Text(text = "Click to add 1")
                        }

                    }

                }
            }
        }
    }
}

