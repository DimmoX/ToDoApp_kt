package com.example.todoapp.views

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.todoapp.components.backgroundImageHome
import com.example.todoapp.data.models.TareasModel
import com.example.todoapp.data.models.UsuariosModel
import com.example.todoapp.ui.navigation.NavigationDrawer
import com.example.todoapp.ui.viewmodel.LoginViewModel
import com.example.todoapp.ui.viewmodel.TareasViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun homeScreen(loginViewModel: LoginViewModel, navController: NavController) {
    val usuario = loginViewModel.usuario.value
    val tareasViewModel: TareasViewModel = viewModel()
    val tareas = tareasViewModel.tareas.observeAsState(emptyList())

    var showCard by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        tareasViewModel.obtenerTareas()
    }

    Box(modifier = Modifier.fillMaxSize()){
        NavigationDrawer(navController) { paddingValues ->
            Scaffold(
                content = {
                    Content(usuario, tareas.value)
                },
                floatingActionButton = {
                    FAB(onClick = { showCard = true })
                },
                floatingActionButtonPosition = FabPosition.End
            )
        }

        if (showCard) {
            TaskCard(onClick = { showCard = false })
        }
    }
}

@Composable
fun FAB(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = { onClick() },
        containerColor = Color(0xFF95bbb6),
        contentColor = Color.White,
        modifier = Modifier
            .padding(16.dp)
            .width(160.dp)
            .height(80.dp),
        elevation = FloatingActionButtonDefaults.elevation(6.dp),
        shape = RoundedCornerShape(50),
    ) {
        Text(
            text = "Crear Tarea",
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            ),
        )
    }
}

@Composable
fun Content(usuario: UsuariosModel?, tareas: List<TareasModel>) {

    var cantTareasPendientes = 0
    var cantTareasCompletadas = 0

    tareas.forEach { tarea ->
        if (tarea.status == true) {
            cantTareasPendientes++
        } else {
            cantTareasCompletadas++
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize(),
        contentAlignment = Alignment.Center

    ) {
        backgroundImageHome(modifier = Modifier.fillMaxWidth())
    }
    Box(
        modifier = Modifier
            .background(Color(0xFFbce1dc)),
        contentAlignment = Alignment.Center
    ) {
        Column(
        ) {
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .padding(top = 120.dp)
            ) {
                usuario?.let {
                    Text(
                        text = "Bienvenido ${it.nombre} ${it.apellido} !",
                        style = TextStyle(
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    )
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxSize(),
                contentAlignment = Alignment.Center

            ) {
                Column {
                    Text(
                        text = "Tareas Pendientes : $cantTareasPendientes",
                        style = TextStyle(
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Tareas Completadas : $cantTareasCompletadas",
                        style = TextStyle(
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskCard(onClick: () -> Unit) {
    var tituloTarea by remember { mutableStateOf("") }
    var descripcionTarea by remember { mutableStateOf("") }

    val tareasViewModel: TareasViewModel = viewModel()

    val context = LocalContext.current
    val speechRecognizer = remember { SpeechRecognizer.createSpeechRecognizer(context) }
    val recognizerIntent = remember {
        Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, "es-ES")
        }
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            speechRecognizer.startListening(recognizerIntent)
        } else {
            Toast.makeText(context, "Permisos del micrófono denegados", Toast.LENGTH_LONG).show()
        }
    }

    val recognizerListener = object : RecognitionListener {
        override fun onReadyForSpeech(p0: Bundle?) {
            Log.d("SpeechRecognizer", "Listo para escuchar")
        }

        override fun onBeginningOfSpeech() {
            Log.d("SpeechRecognizer", "Escuchando...")
        }

        override fun onRmsChanged(p0: Float) {}
        override fun onBufferReceived(p0: ByteArray?) {}
        override fun onEndOfSpeech() {
            Log.d("SpeechRecognizer", "Procesando...")
        }

        override fun onError(p0: Int) {
            Log.d("SpeechRecognizer", "Error al reconocer la voz")
        }

        override fun onResults(p0: Bundle?) {
            val matches = p0?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
            val recognizedText = matches?.firstOrNull() ?: "No se pudo reconocer"

            if (tituloTarea.isBlank()) {
                tituloTarea = recognizedText
            } else {
                descripcionTarea = recognizedText
            }
        }

        override fun onPartialResults(p0: Bundle?) {}
        override fun onEvent(p0: Int, p1: Bundle?) {}
    }

    speechRecognizer.setRecognitionListener(recognizerListener)

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(16.dp),
            shape = RoundedCornerShape(50.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Crear Tarea",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                // TextField Para El Titulo de la Tarea
                TextField(
                    value = tituloTarea,
                    onValueChange = { tituloTarea = it },
                    label = { Text("Ingresa o dime el título") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, Color.Black, RoundedCornerShape(50.dp)),
                    singleLine = true,
                    trailingIcon = {
                        IconButton(onClick = { permissionLauncher.launch(Manifest.permission.RECORD_AUDIO) }) {
                            Icon(imageVector = Icons.Filled.Mic, contentDescription = "Micrófono")
                        }
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedLabelColor = Color.Black,
                        cursorColor = Color.Black
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                // TextField Para la Descripción de la Tarea
                TextField(
                    value = descripcionTarea,
                    onValueChange = { descripcionTarea = it },
                    label = { Text("Ingresa o dime la descripción") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, Color.Black, RoundedCornerShape(50.dp)),
                    singleLine = true,
                    trailingIcon = {
                        IconButton(onClick = { permissionLauncher.launch(Manifest.permission.RECORD_AUDIO) }) {
                            Icon(imageVector = Icons.Filled.Mic, contentDescription = "Micrófono")
                        }
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedLabelColor = Color.Black,
                        cursorColor = Color.Black
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Button(
                        onClick = { onClick() },
                        modifier = Modifier.padding(end = 8.dp),
                        colors = buttonColors(
                            containerColor = Color(0xFF95bbb6)
                        )
                    ) {
                        Text("Cerrar")
                    }

                    Button(
                        onClick = {
                            if (tituloTarea.isNotBlank() && descripcionTarea.isNotBlank()) {
                                tareasViewModel.agregarTarea(tituloTarea, descripcionTarea)
                            } else {
                                Log.d("TaskCard", "Campos vacíos")
                            }
                            onClick()
                        },
                        colors = buttonColors(
                            containerColor = Color(0xFF95bbb6)
                        )
                    ) {
                        Text("Guardar")
                    }
                }
            }
        }
    }
}
