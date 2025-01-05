package com.example.todoapp.views

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun initialScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ) {
        Text("Bienvenido a la aplicación de tareas pendientes")
        Text("1. Iniciar sesión")
        Text("2. Crear cuenta")
        Text("3. Recuperar contraseña")
        Text("4. Salir")
        Text("Seleccione una opción:")
    }
}