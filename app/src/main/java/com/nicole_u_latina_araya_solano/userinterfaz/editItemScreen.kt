package com.heiner.myapplication.userinterfaz

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nicole_u_latina_araya_solano.model.HouseNicole
import com.nicole_u_latina_araya_solano.viewmodel.HouseNicoleViewModel


@Composable
fun EditItemScreen(
    item: HouseNicole,
    viewModel: HouseNicoleViewModel = hiltViewModel(),
    navController: NavController
) {
    var itemName by remember { mutableStateOf(item.name) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TextField(
            value = itemName,
            onValueChange = { itemName = it },
            label = { Text("Nombre del ítem") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                // Actualizamos el ítem en la base de datos
                viewModel.update(item.copy(name = itemName))
                navController.popBackStack() // Regresamos a la pantalla anterior
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar cambios")
        }
    }
}