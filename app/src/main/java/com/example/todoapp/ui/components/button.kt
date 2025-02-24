package devmovil.duocuc.todoapp.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import devmovil.duocuc.todoapp.ui.theme.ColorThree

@Composable
fun button(screen: String, navController: NavController) {

    if(screen == "login_screen"){
        Button(
            onClick = { navController.navigate("login_screen") },
            colors = ButtonDefaults.buttonColors(containerColor = ColorThree),
            modifier = Modifier
                .width(331.dp)
                .height(49.dp)
        ) {
            Text(
                text = "Iniciar Sesión",
                color = Color(0xFFeeedef),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }
    } else if(screen == "create_account_screen"){
        Button(
            onClick = { navController.navigate("create_account_screen") },
            colors = ButtonDefaults.buttonColors(containerColor = ColorThree),
            modifier = Modifier
                .width(331.dp)
                .height(49.dp)
        ) {
            Text(
                text = "Registrarse",
                color = Color(0xFFeeedef),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }
    } else if(screen == "recovery_passwd_screen"){
        Button(
            onClick = { navController.navigate("recovery_passwd_screen") },
            colors = ButtonDefaults.buttonColors(containerColor = ColorThree),
            modifier = Modifier
                .width(331.dp)
                .height(49.dp)
        ) {
            Text(
                text = "Recuperar Contraseña",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }

    } else if(screen == "create_account"){
        Button(
            onClick = { navController.navigate("create_account") },
            colors = ButtonDefaults.buttonColors(containerColor = ColorThree),
            modifier = Modifier
                .width(331.dp)
                .height(49.dp)
        ) {
            Text(
                text = "Crear Cuenta",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }
    } else if(screen == "homeScreen"){
        Button(
            onClick = { navController.navigate("homeScreen")}
        ) { }
    }

}


