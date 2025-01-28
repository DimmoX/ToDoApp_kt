package com.example.todoapp.views

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.todoapp.components.backgroundImageLogin
import com.example.todoapp.components.tituloApp
import com.example.todoapp.ui.components.SwipeHintIcon
import com.example.todoapp.ui.theme.ColorFour
import com.example.todoapp.ui.theme.ColorThree
import com.example.todoapp.ui.viewmodel.LoginViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login(modifier: Modifier = Modifier, navController: NavController, loginViewModel: LoginViewModel) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    var showRecoveryPasswordCard by remember { mutableStateOf(false) }


    val listUsers = loginViewModel.obtenerUsuarios()

    val context = LocalContext.current
    SideEffect {
        if (errorMessage.isNotEmpty()) {
            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
        }
    }

    Log.d("listUsers - Login", "ListUsers: $listUsers")


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFbce1dc))
    ) {

        backgroundImageLogin(modifier = Modifier)


        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                tituloApp(true, navController)

            }

//            TODO: Implementar el SwipeHintIcon
//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(bottom = 100.dp),
//                contentAlignment = Alignment.CenterStart
//            ) {
//
//                SwipeHintIcon(
//                    "left",
//                    modifier = Modifier.align(Alignment.CenterStart)
//                )
//
//                SwipeHintIcon(
//                    "right",
//                    modifier = Modifier.align(Alignment.CenterEnd)
//                )
//            }

        }


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(410.dp)
                .align(Alignment.BottomCenter)
                .clip(RoundedCornerShape(topStart = 200.dp, topEnd = 200.dp))
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
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Spacer(modifier = Modifier.height(80.dp))

                TextField(
                    value = email,
                    onValueChange = { email = it },
                    leadingIcon = { Icon(Icons.Default.Person, null) },
                    label = { Text("Ingresa tu Email") },
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(50.dp),
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedLabelColor = Color.Black
                    )
                )

                Spacer(modifier = Modifier.height(25.dp))

                TextField(
                    value = password,
                    onValueChange = { password = it },
                    leadingIcon = { Icon(Icons.Default.Lock, null) },
                    label = { Text("Ingresa tu Contraseña") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password
                    ),
                    shape = RoundedCornerShape(50.dp),
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedLabelColor = Color.Black
                    ),
                    visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = { showPassword = !showPassword }) {
                            Icon(
                                imageVector = if (showPassword) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                                contentDescription = if (showPassword) "Ocultar Contraseña" else "Mostrar Contraseña"
                            )
                        }
                    }
                )

                Spacer(modifier = Modifier.height(25.dp))

                Box(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {

                    Button(
                        onClick = {
                            val validacionLogin = loginViewModel.validarLogin(email, password)

                            if (validacionLogin == "true") {
                                navController.navigate("homeScreen")

                            } else {
                                errorMessage = "Credenciales incorrectas"
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = ColorThree),
                        modifier = Modifier
                            .width(331.dp)
                            .height(49.dp)
                    ) {
                        Text(
                            text = "Ingresar",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                    }

                }

                TextButton(onClick = {
                    showRecoveryPasswordCard = !showRecoveryPasswordCard
                }){
                    Text(
                        text = "¿Olvidaste tu contraseña?",
                        color = Color.White,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.CenterHorizontally),
                        textDecoration = TextDecoration.Underline
                    )
                }
            }

        }

        if (showRecoveryPasswordCard) {
                Card(
                    modifier = Modifier
                        .align(Alignment.Center)
//                        .padding(20.dp)
                        .fillMaxWidth(0.9f),
//                        .background(Color.Transparent.copy(alpha = 0.5f)),
                    elevation = CardDefaults.cardElevation(5.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White.copy(alpha = 0.5f),
                        contentColor = Color.White
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                    ) {
                        recoveryPasswordScreen(navController = navController)
                    }
                }
        }
    }
}
