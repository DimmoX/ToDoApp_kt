package com.example.todoapp.views

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.todoapp.components.tituloApp
import com.example.todoapp.ui.viewmodel.LoginViewModel

@Composable
fun homeScreen(loginViewModel: LoginViewModel, navController: NavController) {
    val usuario = loginViewModel.usuario.value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFbce1dc)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.align(Alignment.TopCenter)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                tituloApp(true, navController)
            }
        }

        // Coloca el texto centrado
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center // Centra el contenido (el Text) dentro del Box
        ) {
            if (usuario != null) {
                Text("Bienvenido ${usuario.nombre} ${usuario.apellido}")
            } else {
                Text("No hay usuario logueado")
            }
        }
    }
}