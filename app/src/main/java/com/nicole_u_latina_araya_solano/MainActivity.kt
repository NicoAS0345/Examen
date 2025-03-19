package com.nicole_u_latina_araya_solano

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.heiner.myapplication.userinterfaz.Navigation.Navigation
import com.nicole_u_latina_araya_solano.ui.theme.HouseNicoleTheme
import dagger.hilt.android.AndroidEntryPoint

//Esta es la actividad principal de la aplicacion, aqui es donde se empieza a ejecutar la misma
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Aqui se hace que la aplicacion utilice toda la pantalla
        enableEdgeToEdge()
        //Aqui se establece el tema de la aplicacion y la interfaz grafica
        setContent {
            HouseNicoleTheme {
                //Se establece la navegacion que se tiene indicada en la carpeta de interfaz grafica
                Navigation()
            }
        }
    }
}

