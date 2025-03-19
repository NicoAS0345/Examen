package com.nicole_u_latina_araya_solano.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.graphics.Color


private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

//Se genera el tema que va a tener la aplicacion y los colores que va a tener
private val GrayColorScheme = lightColorScheme(
    background = Color(0xFFBDBDBD),
    surface = Color(0xFFBDBDBD),
    primary = Color(0xFF616161),
    onBackground = Color.Black,
    onSurface = Color.Black
)

//Aqui se establece el tema que se genero antes, se establece como el esquema de colores
@Composable
fun HouseNicoleTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = GrayColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}