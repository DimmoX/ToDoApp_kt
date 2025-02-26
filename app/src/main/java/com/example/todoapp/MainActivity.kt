package devmovil.duocuc.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import devmovil.duocuc.todoapp.ui.navigation.vistaDeslizable
import devmovil.duocuc.todoapp.ui.theme.TodoAppTheme
import devmovil.duocuc.todoapp.ui.viewmodel.LoginViewModel
import devmovil.duocuc.todoapp.views.CreateAccount
import devmovil.duocuc.todoapp.views.Login
import devmovil.duocuc.todoapp.views.TareasCompletadas
import devmovil.duocuc.todoapp.views.TareasPendientes
import devmovil.duocuc.todoapp.views.homeScreen
import devmovil.duocuc.todoapp.views.initialScreen


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