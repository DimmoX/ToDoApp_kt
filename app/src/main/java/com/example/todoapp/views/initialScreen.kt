package devmovil.duocuc.todoapp.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import devmovil.duocuc.todoapp.components.VideoBackground
import devmovil.duocuc.todoapp.components.button
import devmovil.duocuc.todoapp.components.tituloApp


@Composable
fun initialScreen(modifier: Modifier = Modifier, navController: NavController) {

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

        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .clip(RoundedCornerShape(topStart = 120.dp, topEnd = 120.dp))
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF82a29d).copy(alpha = 0.9f),
                            Color(0xFF95bbb6).copy(alpha = 0.4f),
                            Color(0xFF9dc4be).copy(alpha = 0.2f)
                        ),
                        startY = 1000f,
                        endY = 0f
                    )
                )
                .align(Alignment.BottomCenter)

        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 80.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                button("login_screen", navController)
                Spacer(Modifier.height(16.dp))
                button("create_account_screen", navController)
            }
        }
    }
}