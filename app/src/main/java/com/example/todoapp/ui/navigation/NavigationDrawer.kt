package com.example.todoapp.ui.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch

/**
 * NavigationDrawer, permite realizar una navegación a través de un menú lateral.
 * @param navController NavController, controlador de navegación.
 * @param content Composable, contenido de la pantalla.
 */
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NavigationDrawer(
    navController: NavController,
    content: @Composable (PaddingValues) -> Unit
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = true,
        drawerContent = {
            DrawerContent(
                onItemClick = {
                    scope.launch { drawerState.close() }
                    navController.navigate(it)
                }
            )
        },
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "ToDoApp",
                                fontSize = 40.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                            )
                        }
                    },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Menú lateral",
                                tint = Color.White
                            )
                        }
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = Color(0xFF95bbb6)
                    )
                )
            },
            content = { paddingValues ->
                content(paddingValues)
            }
        )
    }
}

@Composable
fun DrawerContent(onItemClick: (String) -> Unit) {
    var isPressed by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .width(210.dp)
    ) {

        Spacer(modifier = Modifier.height(120.dp))

        TextButton(
            onClick = {
                onItemClick("homeScreen")
                isPressed = !isPressed
            },
            modifier = Modifier
                .height(80.dp)
                .fillMaxWidth()
                .border(2.dp, Color(0xFF95bbb6), RoundedCornerShape(40)),
            colors = ButtonDefaults.textButtonColors(
                contentColor = Color.White,
                containerColor = if (isPressed) Color(0xFF607A75).copy(alpha = 0.9f) else Color(0xFF607A75).copy(alpha = 0.9f)
            )
        ) {
            Text(
                text = "Inicio",
                style = TextStyle(
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(
            onClick = { onItemClick("tareas_pendientes") },
            modifier = Modifier
                .height(80.dp)
                .fillMaxWidth()
                .border(2.dp, Color(0xFF95bbb6), RoundedCornerShape(40)),
            colors = ButtonDefaults.textButtonColors(
                contentColor = Color.White,
                containerColor = if (isPressed) Color(0xFF607A75).copy(alpha = 0.9f) else Color(0xFF607A75).copy(alpha = 0.9f)
            )
        ) {
            Text(
                text = "Tareas pendientes",
                style = TextStyle(
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(
            onClick = { onItemClick("tareas_completadas") },
            modifier = Modifier
                .height(80.dp)
                .fillMaxWidth()
                .border(2.dp, Color(0xFF95bbb6), RoundedCornerShape(40)),
            colors = ButtonDefaults.textButtonColors(
                contentColor = Color.White,
                containerColor = if (isPressed) Color(0xFF607A75).copy(alpha = 0.9f) else Color(0xFF607A75).copy(alpha = 0.9f)
            )
        ) {
            Text(
                text = "Tareas completadas",
                style = TextStyle(
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            )
        }
    }
}