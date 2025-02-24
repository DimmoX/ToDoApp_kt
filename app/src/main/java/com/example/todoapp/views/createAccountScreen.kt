package devmovil.duocuc.todoapp.views

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import devmovil.duocuc.todoapp.components.BackgroundImageRegister
import devmovil.duocuc.todoapp.components.tituloApp
import devmovil.duocuc.todoapp.ui.theme.ColorThree
import devmovil.duocuc.todoapp.ui.viewmodel.CreateAccountViewModel
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateAccount(modifier: Modifier, navController: NavController) {

    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }
    var resultCreate by remember { mutableStateOf(false) }

    var nombreError by remember { mutableStateOf(false) }
    var apellidoError by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }


    val emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$".toRegex()

    fun validateForm(): Boolean {
        nombreError = nombre.isBlank()
        apellidoError = apellido.isBlank()
        emailError = email.isBlank() || !email.matches(emailRegex)
        passwordError = password.isBlank()

        return !nombreError && !apellidoError && !emailError && !passwordError
    }

    val createAccountViewModel: CreateAccountViewModel = viewModel()
    val agregarUsuarioStatus by createAccountViewModel.agregarUsuarioStatus.observeAsState()

    val context = LocalContext.current

    SideEffect {
        agregarUsuarioStatus?.let {
            if (it) {
                val successMessage = "Usuario agregado exitosamente"
                Toast.makeText(context, successMessage, Toast.LENGTH_SHORT).show()
            } else {
                val errorMessage = "Error al agregar el usuario"
                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .background(Color(0xFFbce1dc))
        ){

            BackgroundImageRegister(modifier = Modifier.padding(bottom = 150.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                tituloApp(true, navController)
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(550.dp)
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
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = Modifier.height(95.dp))

                TextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = { Text("Ingrese Nombre") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(50.dp),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text
                    ),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedLabelColor = Color.Black
                    ),
                    isError = nombreError
                )
                if (nombreError) {
                    Text("Este campo es obligatorio", color = Color.Red, style = MaterialTheme.typography.bodyMedium)
                }

                Spacer(modifier = Modifier.height(16.dp))

                TextField(
                    value = apellido,
                    onValueChange = { apellido = it },
                    label = { Text("Ingresa Apellido") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(50.dp),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text
                    ),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedLabelColor = Color.Black,
                    ),
                    isError = apellidoError
                )
                if (apellidoError) {
                    Text("Este campo es obligatorio", color = Color.Red, style = MaterialTheme.typography.bodyMedium)
                }

                Spacer(modifier = Modifier.height(16.dp))

                TextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Ingrese Email") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(50.dp),
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedLabelColor = Color.Black
                    ),
                    isError = emailError
                )
                if (emailError) {
                    Text("Ingrese un correo válido", color = Color.Red, style = MaterialTheme.typography.bodyMedium)
                }

                Spacer(modifier = Modifier.height(16.dp))

                TextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Ingrese Contraseña") },
                    modifier = Modifier.fillMaxWidth(),
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
                    },
                    isError = passwordError
                )
                if (passwordError) {
                    Text("Este campo es obligatorio", color = Color.Red, style = MaterialTheme.typography.bodyMedium)
                }

                Spacer(modifier = Modifier.height(16.dp))

                Box(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {

                    Button(
                        onClick = {
                            if (validateForm()) {
                                createAccountViewModel.crearUsuario(nombre, apellido, email, password)
                                resultCreate = true
                            }

                        },
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
                }
            }

            if(resultCreate === true){
                toast("Usuario creado exitosamente")

                nombre = ""
                apellido = ""
                email = ""
                password = ""

                LaunchedEffect(Unit) {
                    delay(2000)
                    navController.navigate("login_screen")
                }
            }
        }
    }
}

@Composable
fun toast(msg: String) {
    val context = LocalContext.current
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
}
