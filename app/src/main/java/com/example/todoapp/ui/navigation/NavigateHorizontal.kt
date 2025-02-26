package devmovil.duocuc.todoapp.ui.navigation
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.todoapp.views.analyticsImageIA
import devmovil.duocuc.todoapp.views.mainScreen

/**
 * Vista deslizable que contiene las vistas de la aplicación
 * @param modifier modificador de la vista
 * @param navController controlador de navegación
 */
@Composable
fun vistaDeslizable(modifier: Modifier, navController: NavController) {

    val pagerState = rememberPagerState{ 2 }
    HorizontalPager(
        state = pagerState,
        modifier = Modifier.fillMaxSize()
    ) { page ->
        when (page) {
            0 -> mainScreen(modifier, navController)
//            1 -> initialScreen(modifier, navController)
            1 -> analyticsImageIA()
        }
    }
}

