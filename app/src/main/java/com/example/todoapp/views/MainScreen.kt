package com.example.todoapp.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.todoapp.components.VideoBackground
import com.example.todoapp.components.tituloApp
import com.example.todoapp.ui.components.SwipeHintIcon

@Composable
fun mainScreen(modifier: Modifier, navController: NavController) {
    Box(){
        VideoBackground()

        Column(
            modifier = modifier
                .fillMaxSize()
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                tituloApp(false, navController)

            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 100.dp),
                    contentAlignment = Alignment.CenterStart
                ) {

                    SwipeHintIcon(
                        "right",
                        modifier = Modifier.align(Alignment.CenterEnd)
                    )
                }
            }
        }
    }
}