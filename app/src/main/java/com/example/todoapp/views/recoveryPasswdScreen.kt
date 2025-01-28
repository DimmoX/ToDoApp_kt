package com.example.todoapp.views

import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.todoapp.ui.theme.ColorThree
import com.example.todoapp.ui.viewmodel.recoveryPasswdViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun recoveryPasswordScreen(navController: NavController) {

    val recoveryPasswdViewModel: recoveryPasswdViewModel = viewModel()
    var email by remember { mutableStateOf("") }
    var resultRecovery by remember { mutableStateOf("") }
    var visibilityPasswd by remember { mutableStateOf(false) }

    val recoveryResult by recoveryPasswdViewModel.recoveryResult.observeAsState()

    recoveryResult?.let { result ->
        if (result.success) {
            visibilityPasswd = true
            resultRecovery = "Tu contraseña es: ${result.password}"
        } else {
            visibilityPasswd = false
            resultRecovery = result.message
        }
    }

    val context = LocalContext.current
    SideEffect {
        if (resultRecovery.isNotEmpty()) {
            Toast.makeText(context, resultRecovery, Toast.LENGTH_SHORT).show()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White.copy(0.8f))
    ) {
        Text(
            text = "Recuperar contraseña",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = ColorThree,
            modifier = Modifier.padding(10.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
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
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                if (email.isNotEmpty()) {
                    if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        recoveryPasswdViewModel.recoveryPassword(email)
                    } else {
                        resultRecovery = "Por favor ingresa un correo electrónico válido"
                    }
                } else {
                    resultRecovery = "Por favor ingresa tu correo electrónico"
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = ColorThree),
        ) {
            Text("Recuperar contraseña")
        }



        if (visibilityPasswd) {
            Spacer(modifier = Modifier.height(16.dp))
            Text("Tu contraseña es: ${recoveryResult?.password}", modifier = Modifier.padding(10.dp),
                fontWeight = FontWeight.Bold,
                color = ColorThree
            )

            resultRecovery = ""
        }

        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = {
                navController.navigate("login_screen")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = ColorThree)
        ) {
            Text("Regresar")
        }
    }
}
