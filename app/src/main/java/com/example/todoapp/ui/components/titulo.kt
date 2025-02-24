package devmovil.duocuc.todoapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun tituloApp(viewArrow: Boolean, navController: NavController) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .clip(RoundedCornerShape(bottomStart = 200.dp, bottomEnd = 200.dp))
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF82a29d).copy(alpha = 0.9f),
                        Color(0xFF95bbb6).copy(alpha = 0.4f),
                        Color(0xFF9dc4be).copy(alpha = 0.2f)
                    ),
                    startY = 0f,
                    endY = 1000f
                )
            )
    ){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 50.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = "ToDo App",
                    style = TextStyle(
                        fontSize = 50.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    ),
                    color = Color.White
                )

                Text(
                    text = "Organizate y prioriza tus tareas",
                    style = TextStyle(
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    ),
                    color = Color.White
                )
            }

            if(viewArrow){
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    contentAlignment = Alignment.CenterStart
                ){
                    Spacer(modifier = Modifier.height(80.dp))
                    ArrowBack(navController)
                }
            }
        }
    }
}
