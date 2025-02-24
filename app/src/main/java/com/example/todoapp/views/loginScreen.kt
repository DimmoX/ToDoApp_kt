package com.example.todoapp.views

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
import com.example.todoapp.ui.theme.ColorThree
import com.example.todoapp.ui.viewmodel.LoginViewModel

/**
 * Vista de Login que permite al usuario ingresar a la aplicación
 * @param modifier modificador de la vista
 * @param navController controlador de navegación
 * @param loginViewModel ViewModel de la pantalla de Login
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login(modifier: Modifier = Modifier, navController: NavController, loginViewModel: LoginViewModel) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }
    var showRecoveryPasswordCard by remember { mutableStateOf(false) }

    val context = LocalContext.current

    val loginStatus by loginViewModel.loginStatus.observeAsState()
    val errorMessage by loginViewModel.errorMessage.observeAsState()
    val usuario by loginViewModel.usuario.observeAsState()

    var hasShownLoginToast by remember { mutableStateOf(false) }


    LaunchedEffect(loginStatus) {
        if (loginStatus == "Login exitoso" && usuario != null) {
            if (!hasShownLoginToast) {
                Toast.makeText(context, "Login exitoso", Toast.LENGTH_SHORT).show()
                hasShownLoginToast = true
            }
            navController.navigate("homeScreen") {
                popUpTo("loginScreen") { inclusive = true }
            }
        }
    }

    SideEffect {
        errorMessage?.let {
            if (it.isNotEmpty()) {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
        }
    }

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
                            loginViewModel.validarLogin(email, password)
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
                        .fillMaxWidth(0.8f),
                    elevation = CardDefaults.cardElevation(10.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White.copy(alpha = 0.8f)
                    )
                ) {
                    recoveryPasswordScreen(navController = navController)
                }
        }
    }
}
