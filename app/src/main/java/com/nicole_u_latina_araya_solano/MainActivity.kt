package com.nicole_u_latina_araya_solano

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.heiner.myapplication.userinterfaz.Navigation.Navigation
import com.nicole_u_latina_araya_solano.ui.theme.HouseNicoleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HouseNicoleTheme {
                Navigation()
            }
        }
    }
}

