package devmovil.duocuc.todoapp.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.datasource.RawResourceDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import devmovil.duocuc.todoapp.R

@Composable
fun BackgroundImageRegister(modifier: Modifier = Modifier) {

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.register_animation))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever
    )

    LottieAnimation(
        composition = composition,
        progress = { progress },
        modifier = modifier.fillMaxSize()
    )
}

@Composable
fun backgroundImageLogin(modifier: Modifier){

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.login_animation))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever
    )

    LottieAnimation(
        composition = composition,
        progress = { progress },
        modifier = modifier.fillMaxSize()
    )
}

@Composable
fun backgroundImageHome(modifier: Modifier){

    val image = painterResource(id = R.drawable.home)
    Image(
        painter = image,
        contentDescription = "Imagen del Home",
        modifier = Modifier.fillMaxWidth(),
        contentScale = ContentScale.Crop
    )
}

@SuppressLint("UnsafeOptInUsageError")
@Composable
fun VideoBackground() {
    val context = LocalContext.current

    val exoPlayer = remember {
        ExoPlayer.Builder(context).build()
    }

    exoPlayer.playWhenReady = true

    exoPlayer.repeatMode = ExoPlayer.REPEAT_MODE_ONE

    val uri = RawResourceDataSource.buildRawResourceUri(R.raw.bg)

    val mediaItem = remember{
        MediaItem.Builder()
            .setUri(uri)
            .build()
    }

    exoPlayer.setMediaItem(mediaItem)
    exoPlayer.prepare()

    Box(modifier = Modifier.fillMaxSize()){
        AndroidView(factory = {
            PlayerView(it).apply {
                useController = false
                player = exoPlayer
                resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
            }
        },
            modifier = Modifier.fillMaxSize(),
            onRelease = {
                exoPlayer.release()
            }
        )

        Column(modifier = Modifier.fillMaxSize()){

        }
    }
}