package com.example.todoapp.views

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.todoapp.ui.components.dropdownStatusTareas
import com.example.todoapp.ui.navigation.NavigationDrawer
import com.example.todoapp.ui.viewmodel.TareasViewModel

/**
 * TareasPendientes: Pantalla que muestra las tareas pendientes
 * @param modifier Modificador de diseño para el contenido de la pantalla
 * @param navController Controlador de navegación
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TareasPendientes(modifier: Modifier, navController: NavController) {

    val tareasViewModel: TareasViewModel = viewModel()

    LaunchedEffect(Unit) {
        tareasViewModel.obtenerTareas()
    }

    val topBarHeight = 120.dp

    Box(modifier = Modifier.fillMaxSize()){
        NavigationDrawer(navController) { paddingValues ->
            Scaffold(
                content = {
                    contentTaskPending(tareasViewModel, topBarHeight)
                }
            )
        }
    }
}

/**
 * @param tareasViewModel ViewModel que contiene la lógica de las tareas
 * @param topBarHeight Altura de la barra superior
 */
@Composable
fun contentTaskPending(tareasViewModel: TareasViewModel, topBarHeight: Dp) {
    val tareas = tareasViewModel.tareas.value
    val dropdownOptions = listOf("Pendiente", "Finalizado")
    var selectedStatus by remember { mutableStateOf(dropdownOptions[0]) }
    var isSaveEnabled by remember { mutableStateOf(false) }
    var selectedTaskId by remember { mutableStateOf("") }

    fun onStatusChanged(newStatus: String, taskId: String) {
        selectedStatus = newStatus
        selectedTaskId = taskId
        isSaveEnabled = true
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFbce1dc)),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .padding(top = topBarHeight)
            ) {
                Text(
                    text = "Tareas Pendientes",
                    style = TextStyle(
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                tareas?.forEach { tarea ->
                    if(tarea.status == true){
                        Text(
                            text = "Título: ${tarea.titulo}",
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Start
                            )
                        )
                        Text(
                            text = "Descripción: ${tarea.descripcion}",
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Start
                            )
                        )
                        Text(
                            text = "Fecha: ${tarea.fecha}",
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Start
                            )
                        )
                        Text(
                            text = "Hora: ${tarea.hora}",
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Start
                            )
                        )

                        Row(
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp)
                        ) {
                            Text(
                                text = "Status tarea: ",
                                style = TextStyle(
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Start
                                )
                            )

                            dropdownStatusTareas(dropdownOptions, onStatusChanged = { status -> onStatusChanged(status, tarea.id) })
                        }

                        Text(
                            text = "----------------------------",
                            modifier = Modifier.padding(top = 10.dp),
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                        )
                    }

                }
            }
        }

        if (isSaveEnabled) {
            FloatingActionButton(
                onClick = {
                    if(selectedStatus == "Finalizado"){
                        tareasViewModel.actualizarStatusTarea(selectedTaskId, false)
                    } else {
                        tareasViewModel.actualizarStatusTarea(selectedTaskId, true)
                    }
                    isSaveEnabled = false
                },
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.BottomEnd),
                contentColor = Color.White
            ) {
                Icon(Icons.Default.Save, contentDescription = "Guardar")
            }
        }
    }
}