package devmovil.duocuc.todoapp.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import devmovil.duocuc.todoapp.components.VideoBackground
import devmovil.duocuc.todoapp.components.tituloApp
import devmovil.duocuc.todoapp.ui.components.SwipeHintIcon

/**
 * Main Screen que contiene el video de fondo, el titulo de la app
 * es el primer screen que se muestra al abrir la app
 * @param modifier modificador de la pantalla
 * @param navController controlador de navegación
 */
@Composable
fun mainScreen(modifier: Modifier, navController: NavController) {

    Box(){
        VideoBackground()

        Column(
            modifier = modifier
                .fillMaxSize()
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                tituloApp(false, navController)

            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 100.dp),
                    contentAlignment = Alignment.CenterStart
                ) {

                    SwipeHintIcon(
                        "right",
                        modifier = Modifier.align(Alignment.CenterEnd)
                    )
                }
            }
        }
    }
}