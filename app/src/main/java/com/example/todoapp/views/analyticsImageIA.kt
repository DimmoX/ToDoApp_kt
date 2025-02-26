package com.example.todoapp.views

import android.content.Context
import android.net.Uri
import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import java.util.Locale

@Composable
fun analyticsImageIA(){
    val context = LocalContext.current
    val (recognizedText, setRecognizedText) = remember { mutableStateOf("") }
    val tts = remember { TextToSpeech(context) { } }

    // Configurar idioma del TTS
    tts.language = Locale("es", "ES")

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let { selectedImageUri ->
            processImage(selectedImageUri, context, setRecognizedText)
        }
    }

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Button(onClick = { launcher.launch("image/*") }) {
            Text("Seleccionar Imagen")
        }
        if (recognizedText.isNotEmpty()) {
            Text("Texto detectado: $recognizedText")
            Button(onClick = { speakText(tts, recognizedText) }) {
                Text("Escuchar Texto")
            }
        }
    }
}


/**
 * Procesar la imagen seleccionada y extraer el texto
 */
fun processImage(uri: Uri, context: Context, onTextRecognized: (String) -> Unit) {
    try {
        val image = InputImage.fromFilePath(context, uri)
        val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)

        recognizer.process(image)
            .addOnSuccessListener { visionText ->
                val text = visionText.text
                onTextRecognized(text)
                Log.d("MLKit", "Texto detectado: $text")
            }
            .addOnFailureListener { e ->
                Log.e("MLKit", "Error en reconocimiento de texto", e)
            }
    } catch (e: Exception) {
        Log.e("MLKit", "Error al procesar la imagen", e)
    }
}

/**
 * Leer texto en voz alta
 */
fun speakText(tts: TextToSpeech, text: String) {
    tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
}