package com.example.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todoapp.ui.navigation.vistaDeslizable
import com.example.todoapp.ui.theme.TodoAppTheme
import com.example.todoapp.ui.viewmodel.LoginViewModel
import com.example.todoapp.views.CreateAccount
import com.example.todoapp.views.Login
import com.example.todoapp.views.TareasCompletadas
import com.example.todoapp.views.TareasPendientes
import com.example.todoapp.views.homeScreen
import com.example.todoapp.views.initialScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            TodoAppTheme {
                val navController = rememberNavController()
                val loginViewModel: LoginViewModel by viewModels()

                // Navigación de la aplicación
                NavHost(navController = navController, startDestination = "main_screen") {
                    composable("main_screen") {
                        vistaDeslizable(modifier = Modifier, navController)
                    }
                    composable("initial_screen") {
                        initialScreen(modifier = Modifier, navController)
                    }
                    composable("login_screen") {
                        Login(modifier = Modifier, navController, loginViewModel)
                    }
                    composable("create_account_screen") {
                        CreateAccount(modifier = Modifier, navController)
                    }
                    composable("homeScreen") {
                        homeScreen(loginViewModel, navController)
                    }
                    composable("tareas_completadas") {
                        TareasCompletadas(modifier = Modifier, navController)
                    }
                    composable("tareas_pendientes") {
                        TareasPendientes(modifier = Modifier, navController)
                    }
                }
            }
        }
    }
}